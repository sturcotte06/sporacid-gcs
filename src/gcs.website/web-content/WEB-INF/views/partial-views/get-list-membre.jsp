<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
  
  HtmlAndJavaScript gridControl = ControlHelpers.getGridForObjects(membres, Membre.class, menu);
%>
<div class="management-grid-container">
<!--   <div class="management-grid-header-container jqw-menu-container unselectable"> -->
<!--     <ul class="management-grid-header jqw-menu"> -->
<!--       <li> -->
<!--           <div class="relative"> -->
<%--             <div class="management-grid-header-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-edit.png);"></div> --%>
<!--           </div> -->
<!--           Modifier -->
<!--       </li> -->
<!--       <li> -->
<!--           <div class="relative"> -->
<%--             <div class="management-grid-header-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-delete.png);"></div> --%>
<!--           </div> -->
<!--           Supprimer -->
<!--       </li> -->
<!--       <li> -->
<!--           <div class="relative"> -->
<%--             <div class="management-grid-header-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-search.png);"></div> --%>
<!--           </div> -->
<!--           <a href="javascript:$('.ui-icon-search').click()">Rechercher</a> -->
<!--       </li> -->
<!--       <li> -->
<!--           <div class="relative"> -->
<%--             <div class="management-grid-header-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-refresh.png);"></div> --%>
<!--           </div> -->
<!--           <a href="javascript:$('.ui-icon-refresh').click()">Réinitialiser</a> -->
<!--       </li> -->
<!--       <li> -->
<!--           <div class="relative"> -->
<%--             <div class="management-grid-header-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-export.png);"></div> --%>
<!--           </div> -->
<!--           Exporter... -->
<!--           <ul> -->
<!--             <li>...en pdf</li> -->
<!--             <li>...en csv</li> -->
<!--           </ul> -->
<!--       </li> -->
<!--     </ul> -->
<!--   </div> -->
  <%=gridControl.getHtmlString()%>
</div>
 
<style type="text/css">
  .management-grid-container {
    height: 100%;
  } 
  /*.ui-icon-refresh {
    display: none;
  }
  .ui-icon-search {
    display: none;
  }*/
  /*.management-grid-header {
    height: 50px;
  }*/
</style>

<script type="text/javascript">
  <%=gridControl.getScript()%>
  /*$(".jqw-menu-container").jqxMenu({theme: cJqWidgetTheme});
  $(".ui-icon-refresh, .ui-icon-search").hide();*/
</script>