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


	Menu menu = new Menu();
	menu.addItem(new MenuItem("", context + "/images/metro-ui-icons/metro-add.png", "#"));
	menu.addItem(new MenuItem("Modifier", context + "/images/metro-ui-icons/metro-edit.png", "#"));
	menu.addItem(new MenuItem("Supprimer", context + "/images/metro-ui-icons/metro-delete.png", "#"));
		
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
			<h1>Ajout d'un membre</h1>
		</div>
	</div>
	
	<form id="add-membre-form" class="form form-horizontal" method="post" action="<%=context%>/membres/ajouter">
		<!-- Basic member information -->
		<div class="row">
			<div class="profil-item-container col-md-4">
				<label class="profil-label control-label unselectable" for="concentration">Code Universelle:</label>
				<input type="text" class="form-control" name="nom" value="" />
			</div>
			<div class="profil-item-container col-md-4">
				<label class="profil-label control-label unselectable" for="concentration">Concentration:</label>
				<select class="form-control" name="concentration">
					<% for(ConcentrationBean concentration : concentrations) {  %>
						   <option value="<%=concentration.getId()%>"><%=concentration.getAcronyme()%></option>   	
					<%}%>
				</select>
			</div>
			<div class="form-group">
            	<div class="form-submit col-sm-offset-3 col-sm-6 unselectable">Ajouter membre</div>
            </div>
		</div>
	</form>
</div>
<script type="text/javascript">

</script>