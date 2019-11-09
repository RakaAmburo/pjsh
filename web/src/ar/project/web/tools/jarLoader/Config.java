package ar.project.web.tools.jarLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class Config {

	private static final String CLASS_LOCATION = "WebContent/WEB-INF/classes";
	private static final String SRC_LOCATION = "src";
	private static final String PROPS_CLASSPATH_END_LOCATION = "/web/WebContent/WEB-INF/classes/config.properties";
	private static final String NEW_CLASSPATH_END = "";
	private static final String FOLDER_CLASSPATH_KEY = "folder.class.path";
	private static final String CONFIG_PROPERTIES_NAME = "config.properties";
	private static final String GENERAL_PROPERTIES_PATH = "/ear/commons/general.properties";
	private static final String ENCRYPT_REGEXP = "CNE\\((.+)\\)";
	private static final Pattern ENCPAT = Pattern.compile(ENCRYPT_REGEXP);
	private static final String ENC_STORE = "/ent/src/enckeystore.jceks";
	private static final String ENC_STORE_PSWD = "123456";

	private static final String[] OTHER_PROPS = { "/rest/src/config.properties", "/webServ/src/config.properties",
			"/mvc/src/config.properties" };

	/**
	 * The purpose of this is to set all the class path variables needed to
	 * start the application. And to encrypt the values of general.properties
	 * enclosed in CNE({value}).
	 * 
	 * @param args
	 * @throws IOException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws CertificateException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws InvalidKeyException
	 * @throws UnrecoverableKeyException
	 */
	public static void main(String[] args) throws IOException, UnrecoverableKeyException, InvalidKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		InputStream in = Config.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES_NAME);

		String webInfPath = Config.class.getClassLoader().getResource(CONFIG_PROPERTIES_NAME).getPath();
		String newPath = webInfPath.replaceAll(CLASS_LOCATION, SRC_LOCATION);
		String folderPath = webInfPath.replaceAll(PROPS_CLASSPATH_END_LOCATION, NEW_CLASSPATH_END);

		FileOutputStream out = new FileOutputStream(newPath);

		Properties properties = new Properties();
		properties.load(in);

		in.close();

		properties.setProperty(FOLDER_CLASSPATH_KEY, folderPath);
		properties.store(out, null);

		out.close();

		for (int i = 0; i < OTHER_PROPS.length; i++) {

			FileInputStream inOther = new FileInputStream(folderPath + OTHER_PROPS[i]);

			FileOutputStream outOther = new FileOutputStream(folderPath + OTHER_PROPS[i]);

			Properties otherProp = new Properties();
			otherProp.load(inOther);

			inOther.close();

			otherProp.setProperty(FOLDER_CLASSPATH_KEY, folderPath);
			otherProp.store(outOther, null);

			outOther.close();

		}

		// Variables encryption:
		File gralPropsFile = new File(folderPath + GENERAL_PROPERTIES_PATH);
		List<String> lines = Files.readAllLines(gralPropsFile.toPath(), Charset.defaultCharset());
		List<String> newLines = new ArrayList<String>();
		for (String line : lines) {
			if (line.contains("=") && !line.startsWith("#")) {
				String[] keyValue = line.split("=");
				Matcher matcher = ENCPAT.matcher(keyValue[1]);
				if (matcher.matches()) {
					line = String.format("%s=ENC(%s)", keyValue[0],
							encryptString(folderPath + ENC_STORE, matcher.group(1)));
				}
			}
			newLines.add(line);
			/// System.out.println(line);
		}
		
		Files.write(gralPropsFile.toPath(), newLines, Charset.forName("UTF-8"));

	}

	private static String encryptString(String encStore, String toEnc)
			throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException,
			IOException, UnrecoverableKeyException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException {

		KeyStore keyStore = KeyStore.getInstance("JCEKS");
		keyStore.load(new FileInputStream(encStore), ENC_STORE_PSWD.toCharArray());

		Key key = keyStore.getKey("encryption", ENC_STORE_PSWD.toCharArray());

		// System.out.println(key.toString());

		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedMessageInBytes = cipher.doFinal((toEnc.getBytes("UTF-8")));

		Base64 base64Encoder = new Base64();
		byte[] base64EncodedByteArray = base64Encoder.encode(encryptedMessageInBytes);
		String base64EncodedMessage = new String(base64EncodedByteArray);

		// System.out.println(base64EncodedMessage);
		return base64EncodedMessage;

	}

}
