<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="gcs.website.utils.SessionUtils"%>
<%@page import="gcs.website.views.beans.WsSession"%>
    
<%
  String context = (String) request.getContextPath();
  WsSession wsSession = SessionUtils.getWsSession(request.getSession());
%>
<div class="status-bar-container unselectable">
  <ul class="status-bar">
  	<li id="user_preferences_item" class="status-bar-item">
      <div class="status-bar-item-image" style="background-image: url(http://etc-mysitemyway.s3.amazonaws.com/icons/legacy-previews/icons/simple-red-square-icons-business/128612-simple-red-square-icon-business-gears-sc37.png);">
        <a class="status-bar-item-link" href="<%=context%>/usager/preferences"></a>
      </div>
  	</li>
  	<li id="user_dashboard_item" class="status-bar-item">
      <div class="status-bar-item-image" style="background-image: url(http://2.bp.blogspot.com/_UM_s814EKNk/TA8AzMxYlQI/AAAAAAAACYU/BlOoxuBFs5I/s320/Extra-Dashboard-256x256.png);">
        <a class="status-bar-item-link" href="<%=context%>/usager/dashboard"></a>
      </div>
  	</li>
  	<li id="club_selector_item" class="status-bar-item">
      <form id="form_club_selection" class="form" method="post" action="<%=context%>/usager/modifier-contexte">
        <input id="context_id_input" type="hidden" name="contextId" value="" />
        <div id="club_selector" class="jqw-dropdownlist"></div>
      </form>
  	</li>
  	<li id="whom_item" class="status-bar-item float-right-item">
      <div class="status-bar-item-content">
        Bienvenue, <%=wsSession.getUsername()%> [ <a class="status-bar-item-link" href="<%=context%>/public/deconnexion">Déconnexion</a> ]
      </div>
      <%--<div class="status-bar-item-content">
        <a class="status-bar-item-link" href="#" 
          onclick="javascript:loadContentAjax('<%=context%>/usager/acces-securise/connexion', 'get')">Connexion</a>
      </div> --%>
  	</li>
    <li id="search_item" class="status-bar-item float-right-item">
      <form id="form_website_search" class="form" method="post" action="<%=context%>/rechercher">
        <input type="text" class="form-input form-textbox" name="searchCriteria" placeholder="Rechercher..." />
        <div class="form-submit">
          Go
        </div>
      </form>
    </li>
  </ul>
</div>

<script type="text/javascript">
  $(document).ready(
    function () {
      var source = [
        {id: 1, value: "Chinook"}, 
        {id: 2, value: "Évolution"},
        {id: 3, value: "Applets"}
      ];
      $("#club_selector").data(cJqWidgetDataSourceKey, source);
      $("#club_selector").on("change", 
        function () {
          var currentItem = $(this).jqxDropDownList("getSelectedItem"); 
          $("#context_id_input").val(currentItem.id);
          $("#form_club_selection").submit();
      	}  
      );
    } 
  );
</script>
