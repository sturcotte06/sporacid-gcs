<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="gcs.website.views.helpers.ControlHelpers"%>
<%@page import="gcs.website.views.helpers.models.Menu"%>
<%@page import="gcs.website.views.helpers.models.MenuItem"%>
<%@page import="gcs.website.models.Membre"%>
<%@page import="gcs.website.views.helpers.HtmlAndJavaScript;"%>

<%
  String context = (String) request.getContextPath();
  
  @SuppressWarnings("unchecked")
  Collection<Membre> membres = (Collection<Membre>) request.getAttribute("listeMembres");
  
  Menu menu = new Menu();
  menu.addItem(new MenuItem("Modifier", context + "/images/metro-ui-icons/metro-edit.png", "#"));
  menu.addItem(new MenuItem("Supprimer", context + "/images/metro-ui-icons/metro-delete.png", "#"));
  menu.addItem(new MenuItem("Rechercher", context + "/images/metro-ui-icons/metro-search.png", "javascript:$('.ui-icon-search').click()"));
  menu.addItem(new MenuItem("Réinitialiser", context + "/images/metro-ui-icons/metro-refresh.png", "javascript:$('.ui-icon-refresh').click()"));
  
  MenuItem exportItem = new MenuItem("Exporter...", context + "/images/metro-ui-icons/metro-export.png", "#");
  exportItem.setSubMenu(new Menu());
  exportItem.getSubMenu().addItem(new MenuItem("...en Pdf", "", "#"));
  exportItem.getSubMenu().addItem(new MenuItem("...en Csv", "", "#"));
  menu.addItem(exportItem);
  
  HtmlAndJavaScript gridControl = ControlHelpers.getGridForObjects(membres, Membre.class, menu);
%>

<div id="list_membres_main_content">
  <div id="management_grid_membres" class="main-content-1" style="height: 100%;">
    <%=gridControl.getHtmlString()%>
  </div>
  <div id="current_membre" class="main-content-2 text-center" style="height: 100%;"></div>
</div>

<style type="text/css">
#current_membre {
	background-color: #3E3E42;
	border-color: #35353A;
	color: #ddd;
}
</style>

<script type="text/javascript">
	/**
	 * Create the main content container.
	 */
	$(document).ready(function() {
		$("#list_membres_main_content").gcsMainContent({
			orientation : "horizontal"
		});
	});
	
	/**
	 * Import the grid's javascript.
	 */
    <%=gridControl.getScript()%>
    
	/**
	 * Import the grid's javascript.
	 */
	$(document).ready(function() {
		$("#management_grid_membres").find(".gcs-grid").jqGrid('setGridParam', {
			onSelectRow : function(rowId) {
				var firstName = $(this).jqGrid('getCell', rowId, 'prenom');
				var lastName = $(this).jqGrid('getCell', rowId, 'nom');
				$("#current_membre").html(
						"<h1>Membre <small class=\"current-membre-code-universel\">" + firstName + " "
								+ lastName + "</small></h1>");
			}
		});
	});
</script>