package gcs.webapp.utils.security;

import lombok.Getter;
import lombok.Setter;

public class HashResult 
{
   @Getter @Setter
	private String hashedString;
   
   @Getter @Setter
	private String salt;
}
