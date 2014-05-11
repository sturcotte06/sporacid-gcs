package gcs.webservices.client.requests.usagers;

import gcs.webservices.client.requests.AuthenticatedRequest;

import java.util.Map;

import javax.validation.constraints.NotNull;

/**
 * @author Simon Turcotte-Langevin
 */
public class EditPreferenceRequest extends AuthenticatedRequest
{
    @NotNull(message = "usagers_preferences_notnull")
    private Map<String, String> preferences;

    /**
     * @return the preferences
     */
    public Map<String, String> getPreferences()
    {
        return preferences;
    }

    /**
     * @param preferences the preferences to set
     */
    public void setPreferences(Map<String, String> preferences)
    {
        this.preferences = preferences;
    }
}
