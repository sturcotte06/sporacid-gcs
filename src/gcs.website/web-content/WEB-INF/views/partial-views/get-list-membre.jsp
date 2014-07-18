<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@page import="java.util.Collection"%>
<%@page import="gcs.website.views.helpers.ControlHelpers"%>
<%@page import="gcs.website.views.helpers.models.Menu"%>
<%@page import="gcs.website.views.helpers.models.MenuItem"%>
<%@page import="gcs.webservices.client.models.membres.BaseMembreBean"%>
<%@page import="gcs.website.views.helpers.HtmlAndJavaScript;"%>

<%
    String context = (String) request.getContextPath();
  
  @SuppressWarnings("unchecked")
  Collection<BaseMembreBean> membres = (Collection<BaseMembreBean>) request.getAttribute("listeMembres");
  
  Menu menu = new Menu();

  menu.addItem(new MenuItem("Ajouter", context + "/images/metro-ui-icons/metro-add.png", "javascript:showAddMembreDialogue()"));
  menu.addItem(new MenuItem("Modifier", context + "/images/metro-ui-icons/metro-edit.png", "#"));
  menu.addItem(new MenuItem("Supprimer", context + "/images/metro-ui-icons/metro-delete.png", "#"));
  menu.addItem(new MenuItem("Rechercher", context + "/images/metro-ui-icons/metro-search.png", "javascript:$('.ui-icon-search').click()"));
  menu.addItem(new MenuItem("Réinitialiser", context + "/images/metro-ui-icons/metro-refresh.png", "javascript:$('.ui-icon-refresh').click()"));
  
  MenuItem exportItem = new MenuItem("Exporter...", context + "/images/metro-ui-icons/metro-export.png", "#");
  exportItem.setSubMenu(new Menu());
  exportItem.getSubMenu().addItem(new MenuItem("...en Pdf", "", "#"));
  exportItem.getSubMenu().addItem(new MenuItem("...en Csv", "", "#"));
  menu.addItem(exportItem);
  
  HtmlAndJavaScript gridControl = ControlHelpers.getGridForObjects(membres, BaseMembreBean.class, menu);
%>

<div id="list_membres_main_content">
  <div id="management_grid_membres" class="main-content-1" style="height: 100%;">
    <%=gridControl.getHtmlString()%>
  </div>
  <div id="current_membre" class="main-content-2" style="height: 100%;"></div>
</div>

<div id="dialogAddMembre">
	<div></div>
	<div></div>
</div>
<style type="text/css">
  #current_membre {
    padding-left: 30px;
    padding-right: 30px;
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
			orientation : "vertical"
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
						"<h1>Membre - <small class=\"current-membre-code-universel\">" + firstName + " "
								+ lastName + "</small></h1>");
			}
		});
	});
	
	function showAddMembreDialogue()
		{
			var $DialogAddMembre = $("#dialogAddMembre");
			$DialogAddMembre.jqxWindow({ height:250, width: 525, theme: cJqWidgetTheme });
			$DialogAddMembre.find(".jqx-window-content").loadContentAjax(cContext + '/membres/ajouter', 'get');
			$DialogAddMembre.find(".jqx-window-content").css('overflow', 'hidden');
			return false; ////cancel eventbubbeling
		}
</script>