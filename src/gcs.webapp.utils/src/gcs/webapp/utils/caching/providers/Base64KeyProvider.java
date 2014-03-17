package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64KeyProvider implements IKeyProvider
{
	/**
	 * Transform an object into a cache key
	 * @param object 
	 * @param classObj
	 * @return
	 */
	@Override
	public <E> CacheKey toKey(E object, Class<E> classObj)
	{
		CacheKey key = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		
		try {
			out = new ObjectOutputStream(bos);   
			out.writeObject(object);
			
			// Get the object bytes
			byte[] objBytes = bos.toByteArray();
			
			// Get the string from the bytes then build
			// a new key
			key = new CacheKey(new String(Base64.encodeBase64(objBytes)));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return key;
	}
}
