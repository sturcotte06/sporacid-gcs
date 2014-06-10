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

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = -5120041680296751894L;

    @NotNull(message = "webservices_clientmodels_id_notnull")
    private int id;

    @NotNull(message = "webservices_clientmodels_unit_notnull")
    private UnitBean unit;

    @Size(min = 1, max = 255, message = "webservices_clientmodels_description_size")
    @NotNull(message = "webservices_clientmodels_description_notnull")
    private String description;

    @Size(min = 1, max = 32, message = "webservices_clientmodels_codeclub_size")
    private String codeClub;

    @Digits(integer = 4,fraction = 3)
    @NotNull(message = "webservices_clientmodels_qtycourante_notnull")
    private Double qtyCourante;

    @Digits(integer = 4,fraction = 3)
    private Double qtyMin;

    @Digits(integer = 4,fraction = 3)
    private Double qtyMax;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UnitBean getUnit() {
		return unit;
	}

	public void setUnit(UnitBean unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeClub() {
		return codeClub;
	}

	public void setCodeClub(String codeClub) {
		this.codeClub = codeClub;
	}

	public Double getQtyCourante() {
		return qtyCourante;
	}

	public void setQtyCourante(Double qtyCourante) {
		this.qtyCourante = qtyCourante;
	}

	public Double getQtyMin() {
		return qtyMin;
	}

	public void setQtyMin(Double qtyMin) {
		this.qtyMin = qtyMin;
	}

	public Double getQtyMax() {
		return qtyMax;
	}

	public void setQtyMax(Double qtyMax) {
		this.qtyMax = qtyMax;
	}

}
