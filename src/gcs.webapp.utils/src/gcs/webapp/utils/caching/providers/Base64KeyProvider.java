package gcs.webapp.utils.caching.providers;

import gcs.webapp.utils.caching.CacheKey;
import gcs.webapp.utils.exceptions.InternalException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

public class Base64KeyProvider implements IKeyProvider
{
    /**
     * Transform an object into a cache key.
     * 
     * @param value
     * @return
     */
    @Override
    public <TValue> CacheKey toKey(TValue value)
    {
        CacheKey key = null;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(value);

            // Get the object bytes
            byte[] objBytes = bos.toByteArray();

            // Get the string from the bytes then build
            // a new key
            key = new CacheKey(new String(Base64.encodeBase64(objBytes)));
        } catch (IOException ex) {
            throw new InternalException("caching_keyprovider_serialization_failure", ex);
        }
        
        return key;
    }
}
