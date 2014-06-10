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

import gcs.webapp.utils.hibernate.AbstractModelObject;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UnitBean extends AbstractModelObject implements Serializable
{
    /** */
    private static final long serialVersionUID = -3227719600945663302L;

    @NotNull(message = "webservices_unitbeanmodels_id_notnull")
    private int id;

    @Size(min = 1, max = 8, message = "webservices_unitbeanmodels_unitCode_size")
    @NotNull(message = "webservices_unitbeanmodels_unitCode_notnull")
    private String unitCode;

    @Size(min = 1, max = 12, message = "webservices_unitbeanmodels_systeme_size")
    @NotNull(message = "webservices_unitbeanmodels_systeme_notnull")
    private String systeme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getSysteme() {
		return systeme;
	}

	public void setSysteme(String systeme) {
		this.systeme = systeme;
	}

}
