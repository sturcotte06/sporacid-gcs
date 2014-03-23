<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.Collection"%>
<%@page import="gcs.website.views.helpers.ControlHelpers"%>
<%@page import="gcs.website.models.Membre"%>
<%@page import="gcs.website.views.helpers.HtmlAndJavaScript;"%>
    
<%
  String context = (String) request.getContextPath();

  @SuppressWarnings("unchecked")
  Collection<Membre> membres = (Collection<Membre>) request.getAttribute("listeMembres");
  HtmlAndJavaScript gridControl = ControlHelpers.getGridForObjects(membres, Membre.class, "management-grid jq-grid");
%>
<div class="management-grid-container">
  <%=gridControl.getHtmlString()%>
</div>
<script type="text/javascript">
  <%=gridControl.getScript()%>
  $(window).resize();
</script>
 
<style type="text/css">
  .management-grid-container {
    height: 100%; 
    padding: 2px; 
  } 
</style>