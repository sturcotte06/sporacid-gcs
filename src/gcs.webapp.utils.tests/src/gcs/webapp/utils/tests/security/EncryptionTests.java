package gcs.webapp.utils.tests.security;

import static org.junit.Assert.*;
import gcs.webapp.utils.security.EncryptionResult;
import gcs.webapp.utils.security.IEncryptionProvider;

import org.junit.Test;

public class EncryptionTests {

	private final IEncryptionProvider[] providers = {
			
	};
	
	@Test
	public void test() {
		final String toEncrypt1 = "ajksfhHY?|7¦¢£";
		final String toEncrypt2 = "8asf13nkjasASD";
		for (IEncryptionProvider provider : providers) {
			EncryptionResult result1 = provider.encrypt(toEncrypt1);
			EncryptionResult result2 = provider.encrypt(toEncrypt2);
			
			assertNotEquals("Encryption failed (1).", toEncrypt1, result1.getEncryptedString());
			assertNotEquals("Encryption failed (2).", toEncrypt2, result2.getEncryptedString());
			assertNotEquals("Encryption always give same result.", result1.getEncryptedString(), result2.getEncryptedString());
			
			assertEquals("Decryption failed (1).", toEncrypt1, provider.decrypt(result1));
			assertEquals("Decryption failed (2).", toEncrypt2, provider.decrypt(result2));
		}
	}

}
