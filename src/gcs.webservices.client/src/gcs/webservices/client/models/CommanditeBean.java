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

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommanditeBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 6205999501552864331L;

    @NotNull(message = "webservices_commanditebeanmodels_id_notnull")
    private int id;

    private FournisseurBean fournisseur = new FournisseurBean();

    @NotNull(message = "webservices_commanditebeanmodels_item_notnull")
    private ItemBean item = new ItemBean();

    @NotNull(message = "webservices_commanditebeanmodels_club_notnull")
    private ClubBean club = new ClubBean();

    @Digits(integer = 4,fraction = 2)
    @NotNull(message = "webservices_commanditebeanmodels_valeur_notnull")
    private Double valeur;

    @NotNull(message = "webservices_commanditebeanmodels_nature_notnull")
    @Size(min = 1, max = 64, message = "webservices_commanditebeanmodels_nature_size")
    private String nature;
    
    @NotNull(message = "webservices_commanditebeanmodels_suivies_notnull")
    private Set<SuivieBean> suivies = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FournisseurBean getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(FournisseurBean fournisseur) {
		this.fournisseur = fournisseur;
	}

	public ItemBean getItem() {
		return item;
	}

	public void setItem(ItemBean item) {
		this.item = item;
	}

	public ClubBean getClub() {
		return club;
	}

	public void setClub(ClubBean club) {
		this.club = club;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public Set<SuivieBean> getSuivies() {
		return suivies;
	}

	public void setSuivies(Set<SuivieBean> suivies) {
		this.suivies = suivies;
	}
        
}
