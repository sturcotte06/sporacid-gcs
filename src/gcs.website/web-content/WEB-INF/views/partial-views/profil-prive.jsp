<%@page import="java.util.Collection"%>
<%@page import="gcs.website.views.helpers.ControlHelpers"%>
<%@page import="gcs.webservices.client.models.ConcentrationBean"%>
<%@page import="gcs.website.views.helpers.HtmlAndJavaScript;"%>

<%
	String context = (String) request.getContextPath();

	@SuppressWarnings("unchecked")
	Collection<ConcentrationBean> concentrations = (Collection<ConcentrationBean>) request.getAttribute("listeConcentrations");
%>
<style type="text/css">

#profil-container{
	padding-left: 25px;
}

.header-row h1 {
	color: whitesmoke;
}

.profil-label {
    color: whitesmoke;
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

<div id="profil-container ">
	<div class="row header-row">
		<div class="col-md-12">
			<h1>Bienvenue sur votre profil</h1>
		</div>
	</div>
	<!-- Basic member information -->
	<div class="row">
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="nom">Nom:</label>
			<input type="text" class="form-control" name="nom" value="" />
		</div>
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="prenom">Prénom:</label>
			<input type="text" class="form-control" name="prenom" value="" />
		</div>
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="concentration">Concentration:</label>
			<select class="form-control" name="concentration">
				<% for(ConcentrationBean concentration : concentrations) {%>
					<option value="<%=concentration.getId()%>"><%=concentration.getAcronyme()%></option>
				<% }%>
			</select>
		</div>
	</div>
	<div class="row">
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="courriel">Courriel:</label>
			<input type="text" class="form-control" name="courriel" value="" />
		</div>
		<div class="profil-item-container col-md-4">
			<label class="profil-label control-label unselectable" for="telephone">Téléphone:</label>
			<input type="text" class="form-control" name="telephone" value="" />
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
		<div class="col-md-6">
		</div>
		<!-- Contact Urgence -->
		<div class="col-md-6">
		</div>
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function() {
		$(".upload-button").click(function(){
			$(this).siblings(".upload-ctrl").click();
		});
	});
	
	function fileSelectHandler(input)
	{
	    if (input.files && input.files[0])
        {
            var reader = new FileReader();
            
            reader.onload = function (e){
            					$(".avatar-image figure img")
            					.attr('src', e.target.result)
            					.width(250)
	                            .height(250);
                            };
                            
        	reader.readAsDataURL(input.files[0]);
    	}
	}
</script>