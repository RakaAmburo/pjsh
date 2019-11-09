package ar.project.secure;

import javax.crypto.Cipher;

public interface CipherProvider {

	Cipher getEncryptionCipher();

	Cipher getDecryptionCipher();

}
