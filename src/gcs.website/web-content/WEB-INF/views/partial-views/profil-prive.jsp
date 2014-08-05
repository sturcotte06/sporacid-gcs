<%@page import="gcs.webservices.client.models.ContactUrgenceBean"%>
<%@page import="gcs.webservices.client.models.AllergieBean"%>
<%@page import="gcs.webservices.client.models.usagers.UserProfileBean"%>
<%@page import="java.util.Collection"%>
<%@page import="gcs.website.views.helpers.ControlHelpers"%>
<%@page import="gcs.webservices.client.models.ConcentrationBean"%>
<%@page import="gcs.website.views.helpers.models.Menu"%>
<%@page import="gcs.website.views.helpers.models.MenuItem"%>
<%@page import="gcs.website.views.helpers.HtmlAndJavaScript;"%>

<%
	String context = (String) request.getContextPath();

	@SuppressWarnings("unchecked")
	Collection<ConcentrationBean> concentrations = (Collection<ConcentrationBean>) request.getAttribute("listeConcentrations");
	
	UserProfileBean userProfile = (UserProfileBean) request.getAttribute("userProfile");
	Collection<AllergieBean> allergies = userProfile.getAllergies();
	Collection<ContactUrgenceBean> contactsUrgences = userProfile.getContactsUrgence();
	
	
	Menu allergiesMenu = new Menu();
	Menu contactUrgenceMenu = new Menu();
	
	MenuItem addButton = new MenuItem("Ajouter", context + "/images/metro-ui-icons/metro-add.png", "#");
	MenuItem editButton = new MenuItem("Modifier", context + "/images/metro-ui-icons/metro-edit.png", "#");
	MenuItem deleteButton = new MenuItem("Supprimer", context + "/images/metro-ui-icons/metro-delete.png", "#");
	
	allergiesMenu.addItem(addButton);
	allergiesMenu.addItem(deleteButton);
	
	contactUrgenceMenu.addItem(addButton);
	contactUrgenceMenu.addItem(editButton);
	contactUrgenceMenu.addItem(deleteButton);
	
	HtmlAndJavaScript allergiesGridControl = ControlHelpers.getGridForObjects(allergies, AllergieBean.class, allergiesMenu);
	HtmlAndJavaScript contactUrgenceGridControl = ControlHelpers.getGridForObjects(contactsUrgences, ContactUrgenceBean.class, contactUrgenceMenu);	
%>

<link rel="stylesheet" type="text/css" href="<%=context%>/styles/partial-views/profil-prive.css" />
<script type="text/javascript" src="<%=context%>/scripts/libraries/jquery.maskedinput.min.js"></script>

<div id="profil-container">
	<div class="row header-row">
		<div class="col-md-12">
			<h1>Bienvenue sur votre profil</h1>
		</div>
	</div>
	<form id="submit_form" class="form form-horizontal" method="post" action="<%=context%>/membres/editer">
		<!-- Basic member information -->
		<div class="row">
			<!-- Personal data -->
			<div class="col-md-5">
				<!-- Nom -->
				<div class="row">
					<div class="profil-item-container col-md-12">
						<label class="profil-label control-label unselectable" for="nom">Nom:</label>
						<input type="text" class="form-control" name="nom" value="<%=userProfile.getNom()%>" />
					</div>
				</div>
				<!-- Prenom -->				
				<div class="row">
					<div class="profil-item-container col-md-12">
						<label class="profil-label control-label unselectable" for="prenom">Prénom:</label>
						<input type="text" class="form-control" name="prenom" value="<%=userProfile.getPrenom()%>" />
					</div>				
				</div>
				<!-- Concentration -->
				<div class="row">
					<div class="profil-item-container col-md-12">
						<label class="profil-label control-label unselectable" for="concentration">Concentration:</label>
						<select class="form-control" name="concentration">
							<% for(ConcentrationBean concentration : concentrations) { 
							       if(userProfile.getConcentration().getId() == concentration.getId()){ %>
								   		<option selected value="<%=concentration.getId()%>"><%=concentration.getAcronyme()%></option>
								<%} else {%>
								   		<option value="<%=concentration.getId()%>"><%=concentration.getAcronyme()%></option>
								<%}%>	   	
							<%}%>
						</select>
					</div>
				</div>
				<!-- Telephone -->
				<div class="row">
					<div class="profil-item-container col-md-12">
						<label class="profil-label control-label unselectable" for="telephone">Téléphone:</label>
						<input type="text" class="form-control telephone-control" name="telephone" value="<%=userProfile.getTelephone()%>" />
					</div>				
				</div>
				<!-- Courriel -->
				<div class="row">
					<div class="profil-item-container col-md-12">
						<label class="profil-label control-label unselectable" for="courriel">Courriel:</label>
						<input type="text" class="form-control" name="courriel" value="<%=userProfile.getCourriel()%>" />
					</div>
				</div>
			</div>
			<!--  Right Section - Avatar Holder -->
			<div class="col-md-7 avatar-container ">
				<!-- Image container -->
				<div class="avatar-image">
					<figure>
						<img src="http://placehold.it/200x200" alt="my avatar :D">
						<figcaption>
							<a class="upload-button">Changer l'image</a>
							<input type="file" class="upload-ctrl" onchange="fileSelectHandler(this);" accept="image/x-png, image/gif, image/jpeg"/>
						</figcaption>
					</figure>
				</div>
				<div class="apply-changes-container">
              		<div class="form-submit unselectable" id="apply-changes-button">Sauvegarder Profil</div>
				</div>
			</div>
		</div>
		<!-- Allergie & Contact Urgence section -->
		<div class= "row allergies-contact-container">
			<!--  Allergies -->
			<div class="col-md-5 allergies-container">
				<label class="profil-label control-label unselectable">Allergies:</label>
				<%=allergiesGridControl.getHtmlString()%>	
			</div>
			<!-- Contact Urgence -->
			<div class="col-md-5 contacts-container">
				<label class="profil-label control-label unselectable">Contacts d'urgence:</label>
				<%=contactUrgenceGridControl.getHtmlString()%>
			</div>
			<!-- Empty buffer -->
			<div class="col-md-2"></div>
		</div>
	</form>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		$(".upload-button").click(function(){
			$(this).siblings(".upload-ctrl").click();
		});
		
		$(".telephone-control").mask('(999)999-9999');
	});
	
	/**
	 * Import the grid's javascript.
	 */
    <%=allergiesGridControl.getScript()%>
    <%=contactUrgenceGridControl.getScript()%>
    
    // Binding Click action on Add Menu item
	$(".gcs-grid-menu > ul > li:first").click(function(){	
		//window.open("./add-membre.jsp");
	});

    // Handle img upload and displays a preview scaled to 200x200
	function fileSelectHandler(input)
	{
	    if (input.files && input.files[0])
        {
            var reader = new FileReader();
            
            reader.onload = function (e)
            				{
            					$(".avatar-image figure img")
            					.attr('src', e.target.result)
            					.width(200)
	                            .height(200);
                            };
                            
        	reader.readAsDataURL(input.files[0]);
    	}
	}
</script>