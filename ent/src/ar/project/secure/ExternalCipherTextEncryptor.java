package ar.project.secure;

import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.log4j.Logger;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.util.text.TextEncryptor;

public class ExternalCipherTextEncryptor implements TextEncryptor {
	
	private static final String CIPHER_CHARSET = "US-ASCII";
    private static final String PLAIN_CHARSET = "UTF-8";
    private Cipher encryptionCipher, decryptionCipher;

    private static final Logger LOG = Logger.getLogger(ExternalCipherTextEncryptor.class);

    private final Base64 base64Codec = new Base64();

    
    public String encrypt(String message) {



        String cipherText = null;
        try {


            byte[] cipherTextBytes = encryptionCipher.doFinal(message.getBytes(PLAIN_CHARSET));

            byte[] cipherTextBase64Bytes = base64Codec.encode(cipherTextBytes);

            cipherText = new String(cipherTextBase64Bytes, CIPHER_CHARSET);



        } catch (IllegalBlockSizeException e) {
            LOG.error("IllegalBlockSizeException, check configs ", e);
        } catch (BadPaddingException e) {
            LOG.error("BadPaddingException, check configs ", e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Unexpected Error while Encoding/Decoding string to/from bytes. ", e);
        }

        return cipherText;
    }

    
    public String decrypt(String encryptedMessage) {

        String plainText = null;

        try {
            byte[] cipherTextBytes = base64Codec.decode(encryptedMessage.getBytes(CIPHER_CHARSET));

            byte[] plainTextBytes = decryptionCipher.doFinal(cipherTextBytes);

            plainText = new String(plainTextBytes, PLAIN_CHARSET);
        } catch (IllegalBlockSizeException e) {
            LOG.error("IllegalBlockSizeException, check configs ", e);
        } catch (BadPaddingException e) {
            LOG.error("BadPaddingException, check configs ", e);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Unexpected Error while Encoding/Decoding string to/from bytes. ", e);
        }

        return plainText;
    }

    /**
     * 
     * Sets the Cipher Provider.
     * 
     * @param provider
     *            the Cipher provider this encryptor should use.
     */
    public void setCipherProvider(CipherProvider provider) {
        encryptionCipher = provider.getEncryptionCipher();
        decryptionCipher = provider.getDecryptionCipher();
    }

}
