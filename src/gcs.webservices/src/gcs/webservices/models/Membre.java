package gcs.webservices.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Membres")
@SequenceGenerator(name = "membres_id_seq", sequenceName = "membres_id_seq", allocationSize = 1)
public class Membre 
{
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "membres_id_seq")
    @Column(name = "id")
	private int id;
	
	@OneToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "concentrations_id", 
		referencedColumnName = "id",
		nullable = false)
	private Concentration concentration;

	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="courriel")
	private String courriel;
	
	@Column(name="code_permanent")
	private String codePermanent;
	
	@Column(name="code_universel")
	private String codeUniversel;
	
	@Column(name="actif")
	private boolean actif;
	
	@Column(name="telephone")
	private boolean telephone;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the concentration
	 */
	public Concentration getConcentration() {
		return concentration;
	}

	/**
	 * @param concentration the concentration to set
	 */
	public void setConcentration(Concentration concentration) {
		this.concentration = concentration;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the courriel
	 */
	public String getCourriel() {
		return courriel;
	}

	/**
	 * @param courriel the courriel to set
	 */
	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	/**
	 * @return the codePermanent
	 */
	public String getCodePermanent() {
		return codePermanent;
	}

	/**
	 * @param codePermanent the codePermanent to set
	 */
	public void setCodePermanent(String codePermanent) {
		this.codePermanent = codePermanent;
	}

	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return the telephone
	 */
	public boolean isTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(boolean telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the codeUniversel
	 */
	public String getCodeUniversel() {
		return codeUniversel;
	}

	/**
	 * @param codeUniversel the codeUniversel to set
	 */
	public void setCodeUniversel(String codeUniversel) {
		this.codeUniversel = codeUniversel;
	}
}
