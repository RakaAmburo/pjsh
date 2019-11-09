package ar.project.secure;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.ClassPathResource;

public class KeyStoreCipherProvider implements CipherProvider {

	
	public static final String KEYSTORE_PASSPHRASE_PROPERTY_KEY = "encryption.key";

	private Cipher encryptionCipher;
	private Cipher decryptionsCipher;

	/**
	 * This constructor is for loading asymmetric-key based crypto systems.
	 * 
	 * @param keyStoreLocation
	 *            the location of the keystore from which to load the symmetric
	 *            key.
	 * @param storeType
	 *            the type of keystore. Many KeyStores only support asymmetric
	 *            keys. The "JCEKS" KeyStore is one that supports symmetric
	 *            keys.
	 * @param encryptionAlias
	 *            the alias or named entry of the encryption key inside the
	 *            store you wish to use.
	 * @param decryptionAlias
	 *            the alias or named entry of the decryption key inside the
	 *            store you wish to use.
	 * @param transformation
	 *            the crypto transformation to apply.A transformation is of the
	 *            form: "algorithm/mode/padding" or "algorithm"
	 * 
	 * 
	 * @throws GeneralSecurityException
	 *             if there is an invalid key or configuration.
	 * @throws IOException
	 *             if the KeyStore cannot be read.
	 */

	public KeyStoreCipherProvider(URL keyStoreLocation, String storeType, String encryptionAlias,
			String decryptionAlias, String transformation) throws GeneralSecurityException, IOException {

		char[] passPhraseChars = getPassPhraseChars();

		KeyStore keyStore = getKeyStore(keyStoreLocation, storeType, passPhraseChars);

		Key encryptionKey = keyStore.getKey(encryptionAlias, passPhraseChars);

		Key decryptionKey = keyStore.getKey(decryptionAlias, passPhraseChars);

		encryptionCipher = getInitializedCipher(Cipher.ENCRYPT_MODE, transformation, encryptionKey);

		decryptionsCipher = getInitializedCipher(Cipher.DECRYPT_MODE, transformation, decryptionKey);

	}

	/**
	 * This constructor is for loading symmetric-key based crypto systems.
	 * 
	 * @param keyStoreLocation
	 *            the location of the keystore from which to load the symmetric
	 *            key.
	 * @param storeType
	 *            the type of keystore. Many KeyStores only support asymmetric
	 *            keys. The "JCEKS" KeyStore is one that supports symmetric
	 *            keys.
	 * @param symmetricKeyAlias
	 *            the alias or named entry of the key inside the store you wish
	 *            to use.
	 * @param transformation
	 *            the crypto transformation to apply.A transformation is of the
	 *            form: "algorithm/mode/padding" or "algorithm"
	 * 
	 * 
	 * @throws GeneralSecurityException
	 *             if there is an invalid key or configuration.
	 * @throws IOException
	 *             if the KeyStore cannot be read.
	 */

	public KeyStoreCipherProvider(URL keyStoreLocation, String storeType, String symmetricKeyAlias,
			String transformation) throws GeneralSecurityException, IOException {

		char[] passPhraseChars = getPassPhraseChars();

		KeyStore keyStore = getKeyStore(keyStoreLocation, storeType, passPhraseChars);

		Key secretKey = keyStore.getKey(symmetricKeyAlias, passPhraseChars);

		encryptionCipher = getInitializedCipher(Cipher.ENCRYPT_MODE, transformation, secretKey);

		decryptionsCipher = getInitializedCipher(Cipher.DECRYPT_MODE, transformation, secretKey);

	}

	public KeyStoreCipherProvider(String keyStoreLocation, String storeType, String symmetricKeyAlias,
			String transformation) throws GeneralSecurityException, IOException {

		char[] passPhraseChars = getPassPhraseChars();

		ClassPathResource cpr = new ClassPathResource(keyStoreLocation);

		KeyStore keyStore = getKeyStore(cpr.getURL(), storeType, passPhraseChars);

		Key secretKey = keyStore.getKey(symmetricKeyAlias, passPhraseChars);

		encryptionCipher = getInitializedCipher(Cipher.ENCRYPT_MODE, transformation, secretKey);

		decryptionsCipher = getInitializedCipher(Cipher.DECRYPT_MODE, transformation, secretKey);

	}

	private Cipher getInitializedCipher(int mode, String transformation, Key encryptionKey)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance(transformation);

		cipher.init(mode, encryptionKey);
		return cipher;
	}

	private char[] getPassPhraseChars() {
		
		/* This should be set on environment variables */
		System.setProperty("encryption.key", "123456");
		/* This should be set on environment variables */
		
		String passPhrase = System.getProperty(KEYSTORE_PASSPHRASE_PROPERTY_KEY);
		return passPhrase != null ? passPhrase.toCharArray() : new char[0];

	}

	private KeyStore getKeyStore(URL keyStoreLocation, String storeType, char[] passPhraseChars)
			throws GeneralSecurityException, IOException {
		KeyStore keyStore = KeyStore.getInstance(storeType);

		keyStore.load(keyStoreLocation.openStream(), passPhraseChars);
		return keyStore;
	}

	@Override
	public Cipher getEncryptionCipher() {
		return encryptionCipher;
	}

	@Override
	public Cipher getDecryptionCipher() {
		return decryptionsCipher;
	}

}
