package gcs.webapp.utils.tests.security;

import static org.junit.Assert.*;
import gcs.webapp.utils.security.HashResult;
import gcs.webapp.utils.security.IHashProvider;
import gcs.webapp.utils.security.SHA512HashProvider;

import org.junit.Test;

public class HashingTests {

	private final IHashProvider[] providers = {
		new SHA512HashProvider()
	};
	
	@Test
	public void test() {
		final String toHash1 = "ajksfhHY?|7¦¢£";
		final String toHash2 = "8asf13nkjasASD";
		for (IHashProvider provider : providers) {
			HashResult result1 = provider.hash(toHash1);
			HashResult result2 = provider.hash(toHash2);
			
			assertNotEquals("Hashing failed (1).", toHash1, result1.getHashedString());
			assertNotEquals("Hashing failed (2).", toHash2, result2.getHashedString());
			assertNotEquals("Hashing always give same result.", result1.getHashedString(), result2.getHashedString());
			assertNotEquals("Hashing always give same salt.", result1.getSalt(), result2.getSalt());
			
			HashResult newResult1 = provider.hash(toHash1, result1.getSalt());
			HashResult newResult2 = provider.hash(toHash2, result2.getSalt());
			
			assertEquals("Hash comparison with same salt failed (1).", newResult1.getHashedString(), result1.getHashedString());
			assertEquals("Hash comparison with same salt failed (2).", newResult2.getHashedString(), result2.getHashedString());
		}
	}

}
