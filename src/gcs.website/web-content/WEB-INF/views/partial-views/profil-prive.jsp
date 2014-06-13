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
	
	
	Menu menu = new Menu();
	menu.addItem(new MenuItem("", context + "/images/metro-ui-icons/metro-add.png", "#"));
	menu.addItem(new MenuItem("Modifier", context + "/images/metro-ui-icons/metro-edit.png", "#"));
	menu.addItem(new MenuItem("Supprimer", context + "/images/metro-ui-icons/metro-delete.png", "#"));
	
	HtmlAndJavaScript allergiesGridControl = ControlHelpers.getGridForObjects(allergies, AllergieBean.class, menu);
	HtmlAndJavaScript contactUrgenceGridControl = ControlHelpers.getGridForObjects(contactsUrgences, ContactUrgenceBean.class, menu);
	
%>
<style type="text/css">

.main-content{
	overflow-y:scroll;
	overflow-x:hidden;
}

#profil-container{
	padding-left: 25px;
}

.header-row h1 {
	color: white;
}

.profil-label {
   color: white;
}

.profil-item-container{
	width: 250px;
	margin-top: 10px;
}

.avatar-container {
	margin-top: 25px;
}

figure { 
	display: block; 
	position: relative; 
	overflow: hidden; 
	width:250px;
	height:250px;	
}

figcaption { 
	position: absolute; 
	background: rgba(0,0,0,0.75); 
	color: white; 
	padding: 10px 0px;
	text-align: center;
  
	opacity: 0.4;
	bottom: 0; 
	width: 100%;
	-webkit-transition: all 0.6s ease;
	-moz-transition:    all 0.6s ease;
	-o-transition:      all 0.6s ease;
}

figcaption:hover {
	opacity: 1;
	cursor: pointer;
} 

.upload-button,
.upload-button:link,
.upload-button:visited,
.upload-button:hover {
	color: whitesmoke;
	text-decoration: none;
}

.upload-ctrl{
	display: none !important;
}
</style>

<div id="profil-container">
	<div class="row header-row">
		<div class="col-md-12">
			<h1>Bienvenue sur votre profil</h1>
		</div>
	</div>
	<!-- Basic member information -->
	<div class="row">
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="nom">Nom:</label>
			<input type="text" class="form-control" name="nom" value="<%=userProfile.getNom()%>" />
		</div>
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="prenom">Prénom:</label>
			<input type="text" class="form-control" name="prenom" value="<%=userProfile.getPrenom()%>" />
		</div>
		<div class="profil-item-container col-md-4">
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
	<div class="row">
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="courriel">Courriel:</label>
			<input type="text" class="form-control" name="courriel" value="<%=userProfile.getCourriel()%>" />
		</div>
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="telephone">Téléphone:</label>
			<input type="text" class="form-control" name="telephone" value="<%=userProfile.getTelephone()%>" />
		</div>
	</div>
	<!-- Avatar section -->
	<div class="avatar-container row">
		<!-- Image container -->
		<div class="avatar-image col-md-4">
			<figure>
				<img src="http://placehold.it/250x250" alt="my avatar :D">
				<figcaption>
					<a class="upload-button">Upload Avatar</a>
					<input type="file" class="upload-ctrl" onchange="fileSelectHandler(this);" accept="image/x-png, image/gif, image/jpeg"/>
				</figcaption>
			</figure>
		</div>
	</div>
	<!-- Allergie & Contact Urgence section -->
	<div class= "row">
		<!--  Allergies -->
		<div class="col-md-5">
			<%=allergiesGridControl.getHtmlString()%>	
		</div>
		<!-- Contact Urgence -->
		<div class="col-md-offset-2 col-md-5">
			<%--<%=contactUrgenceGridControl.getHtmlString() --%>
		</div>
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		$(".upload-button").click(function(){
			$(this).siblings(".upload-ctrl").click();
		});
	});
	
	/**
	 * Import the grid's javascript.
	 */
    <%=allergiesGridControl.getScript()%>
<%--     <%=contactUrgenceGridControl.getScript()*/%> --%> 
    
    // BUILD CALISSE ! 12/06/2014
	
    // Handle img upload and displays a preview scaled to 250x250
	function fileSelectHandler(input)
	{
	    if (input.files && input.files[0])
        {
            var reader = new FileReader();
            
            reader.onload = function (e)
            				{
            					$(".avatar-image figure img")
            					.attr('src', e.target.result)
            					.width(250)
	                            .height(250);
                            };
                            
        	reader.readAsDataURL(input.files[0]);
    	}
	}
</script>