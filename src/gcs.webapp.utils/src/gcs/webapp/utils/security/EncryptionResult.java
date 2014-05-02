package gcs.webapp.utils.security;

import lombok.Getter;
import lombok.Setter;

public class EncryptionResult 
{
   @Getter @Setter
	private String encryptedString;
   
   @Getter @Setter
	private String key;
}
