package gcs.webservices.services;

import gcs.webapp.utils.aspects.logging.Loggable;
import gcs.webapp.utils.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Loggable
@Component
@Path("/utilities")
public class UtilityService
{
    /** The default record delimiter that separates rows in a csv. */
    private static final String cDefaultCsvRecordDelimiter = "\r\n";

    /** The default fields delimiter that separates fields in a csv. */
    private static final String cDefaultCsvFieldDelimiter = ";";

    /** The default null value in a csv. */
    private static final String cDefaultCsvNullValue = "";

    @Autowired
    private Gson jsonParser;

    @POST
    @Produces("text/csv")
    @Path("/csv")
    public Response generateCsv(
            @QueryParam("fieldDelimiter") @DefaultValue(cDefaultCsvFieldDelimiter) String fieldDelimiter,
            @QueryParam("recordDelimiter") @DefaultValue(cDefaultCsvRecordDelimiter) String recordDelimiter,
            @QueryParam("nullValue") @DefaultValue(cDefaultCsvNullValue) String nullValue, String jsonArrayStr)
    {
        JsonElement jsonRootElement = jsonParser.fromJson(jsonArrayStr, JsonElement.class);
        if (!jsonRootElement.isJsonArray()) {
            // We only accept json arrays
            Collection<String> validationErrors = new ArrayList<>();
            validationErrors.add("utility_generatecsv_unsupported");
            throw new ValidationException(validationErrors);
        }

        // TODO Generate header

        StringBuilder csvBuilder = new StringBuilder();
        JsonArray jsonArray = jsonRootElement.getAsJsonArray();

        for (JsonElement jsonElement : jsonArray) {
            if (jsonElement.isJsonPrimitive()) {
                // We're dealing with an array of primitives...
                csvBuilder.append(jsonElement.getAsString());
            } else if (jsonElement.isJsonNull()) {
                // We might need to deal with this another way
                csvBuilder.append(nullValue);
            } else if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                for (Map.Entry<String, JsonElement> flattenedObject : jsonObject.entrySet()) {
                    csvBuilder.append(flattenedObject.getValue().getAsString()).append(fieldDelimiter);
                }

                // Trim the last field delimiter
                csvBuilder.setLength(csvBuilder.length() - fieldDelimiter.length());
            }

            // Append a record delimiter
            csvBuilder.append(recordDelimiter);
        }

        // Return the csv response
        return Response.ok(csvBuilder.toString()).build();
    }
    
    /**
     * 
     * @param unflattened
     * @return
     */
    private Collection<Map<String, Object>> flatten(JsonArray jsonArray) 
    {
        Collection<Map<String, Object>> flattened = new ArrayList<>();
        
        for (JsonElement jsonElement : jsonArray) {
            Map<String, Object> current;
            
            if (jsonElement.isJsonPrimitive()) {
                // We're dealing with an array of primitives...
                current = new HashMap<>();
                current.put("element", jsonElement.getAsString());
            } else if (jsonElement.isJsonNull()) {
                // We might need to deal with this another way
                current = new HashMap<>();
                current.put("element", null);
            } else if (jsonElement.isJsonObject()) {
                current = flatten(jsonElement.getAsJsonObject());
            } else {
                throw new UnsupportedOperationException();
            }
            
            flattened.add(current);
        }
        
        return flattened;
    }
    
    private Map<String, Object> flatten(JsonObject jsonObject)
    {
        return null;
    }

    /**
     * @return the jsonParser
     */
    public Gson getJsonParser()
    {
        return jsonParser;
    }

    /**
     * @param jsonParser the jsonParser to set
     */
    public void setJsonParser(Gson jsonParser)
    {
        this.jsonParser = jsonParser;
    }
}
