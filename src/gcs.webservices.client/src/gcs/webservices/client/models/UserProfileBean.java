package gcs.webservices.client.models;

import java.util.Set;

public class UserProfileBean extends AbstractBean
{
    /** */
    private static final long serialVersionUID = 1295833085694098639L;

    private int id;
    private ConcentrationBean concentration;
    private Set<MembreClubBean> clubs;
    private Set<ContactUrgenceBean> contactsUrgence;
    private Set<AllergieBean> allergies;
    private String nom;
    private String prenom;
    private String courriel;
    private String codePermanent;
    private String codeUniversel;
    private boolean actif;
    private String telephone;
}
