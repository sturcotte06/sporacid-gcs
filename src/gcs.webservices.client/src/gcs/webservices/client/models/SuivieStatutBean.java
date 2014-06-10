/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package gcs.webservices.client.models;

import javax.validation.constraints.NotNull;

public class SuivieStatutBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 2318828983764637833L;

    @NotNull(message = "webservices_suiviestatutmodels_id_notnull")
    private int id;

    @NotNull(message = "webservices_suiviestatutmodels_code_notnull")
    private String code;

    @NotNull(message = "webservices_suiviestatutmodels_description_notnull")
    private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
