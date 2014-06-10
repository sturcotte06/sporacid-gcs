package gcs.webservices.client.models;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class MembreClubBean extends AbstractBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "webservices_membreclubbeanmodels_id_notnull")
    private int id;

	@NotNull(message = "webservices_membreclubbeanmodels_club_notnull")
    private ClubBean club;

	@NotNull(message = "webservices_membreclubbeanmodels_membre_notnull")
    private MembreBean membre;

    private Set<RoleBean> roles;

    private Date dateDebut;

    private Date dateFin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClubBean getClub() {
		return club;
	}

	public void setClub(ClubBean club) {
		this.club = club;
	}

	public MembreBean getMembre() {
		return membre;
	}

	public void setMembre(MembreBean membre) {
		this.membre = membre;
	}

	public Set<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleBean> roles) {
		this.roles = roles;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

   
}
