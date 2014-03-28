<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="gcs.website.utils.SessionUtils"%>
<%@page import="gcs.website.views.beans.WsSession"%>
    
<%
  String context = (String) request.getContextPath();
  WsSession wsSession = SessionUtils.getWsSession(request.getSession());
%>
<div class="status-bar-container jqw-menu-container unselectable">
  <ul class="status-bar jqw-menu">
    <li id="club_selector_item">
        <div class="relative">
          <div class="status-bar-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-club-selection.png);"></div>
        </div>
        Choisissez un club...
        <ul>
            <li>Évolution</li>
            <li>Chinook</li>
            <li>Applets</li>
        </ul>
    </li>
    <li id="user_dashboard_item">
        <div class="relative">
          <div class="status-bar-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-dashboard.png);"></div>
        </div>
        <a class="status-bar-item-link" href="<%=context%>/usager/dashboard">Tableau de bord</a>
    </li>
    <li id="identity_item">
      <div class="relative">
        <div class="status-bar-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-identity.png);"></div>
      </div>
      Bienvenue <%=wsSession.getUsername()%>
    </li>
    <li id="user_preferences_item">
        <div class="relative">
          <div class="status-bar-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-settings.png);"></div>
        </div>
        <a class="status-bar-item-link" href="<%=context%>/usager/preferences">Préférences</a>
    </li>
    <li id="logout_item">
        <div class="relative">
          <div class="status-bar-item-image" style="background-image: url(<%=context%>/images/metro-ui-icons/metro-logout.png);"></div>
        </div>
       <a class="status-bar-item-link" href="<%=context%>/public/deconnexion">Déconnexion</a>
    </li>
  </ul>
</div>
