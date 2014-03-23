<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="gcs.website.views.beans.AuthenticationForm"%>

<%
  String context = (String) request.getContextPath();
  AuthenticationForm form = (AuthenticationForm) request.getAttribute("authenticationForm");
%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Authentification</title>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-top.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="<%=context%>/styles/login.css" />
    <link rel="stylesheet" type="text/css" href="<%=context%>/styles/themes/login.metro-dark.css" />
    
  </head>
  <body>
  
    <div class="ets-bar">
      <div class="ets-logo"></div>
      <a class="ets-link" href="http://www.etsmtl.ca/">Accueil du site web de l'ÉTS</a>
    </div>

    <div class="header-container">
      <div class="logo-container">
        <table><tr><td>
          <div class="logo"></div>
        </td></tr></table>
        <div class="header1">Gestionnaire de club scientifique</div>
      </div>
          
      <div class="gcs-description">
        <div class="header2">GCS, Outil de gestion des clubs scientifiques</div>
        Veuillez vous identifier
        <div class="header3"></div>
        <div class="header3">Avis aux étudiants</div>
        <ul>
          <li>Code d'accès: code d'accès universel (ex: AA12345 seulement, sans le @ens.etsmtl.ca)</li>
          <li>Mot de passe: tel que défini sur votre lettre d'admission ou celui que vous vous êtes choisi</li>
        </ul>
      </div>
    </div>
    
    <div class="content-container">
      <div class="main-content">
        
        <div class="login-form-container">
          <div class="login-form-header">Authentification</div>
          
          <%-- Include the menu in the page --%>
          <jsp:include page="partial-views/layout/messages.jsp"></jsp:include>
          
          <form id="login_form" class="form" method="post" action="<%=context%>/public/connexion">
            <table><tr><td>
              <div class="form-line">
                <label class="form-label" for="username">Code AJXXXXX</label>
                <input type="text" class="form-input form-textbox" name="username" 
                  value="<%=(form.getUsername() != null ? form.getUsername() : "")%>" />
              </div>
              <div class="form-line">
                <label class="form-label" for="password">Mot De Passe</label>
                <input type="password" class="form-input form-textbox" name="password" />
              </div>
              <div class="form-line">
                <div class="form-submit unselectable">
                  S'authentifier
                </div>
              </div>
            </td></tr></table>
          </form>
        </div>
        
        <div class="gcs-description">
          <div class="header2">Mot de passe oublié?</div>
            Vous pouvez réinitialiser votre mot de passe sur 
            <a href="<%=context%>/reinitialiser-mot-de-passe">cette page</a>
            , si vous avez défini une question secrète dans votre profil GCS. 
            Ou sinon, contactez le Bureau du registraire:
            <ul>
              <li>En personne, local A-1130</li>
              <li>Par courriel : <a href="mailto:registraire@etsmtl.ca">registraire@etsmtl.ca</a></li>
              <li>Au téléphone : 514-396-8888</li>
            </ul>
        </div>
      </div>
    </div>
<!--     #293134 -->
<!--     #465358 -->
<!--     #788589 -->
    <%-- Include the right column in the page --%>
    <jsp:include page="partial-views/layout/footer.jsp"></jsp:include>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-bottom.jsp"></jsp:include>
    
  </body>
</html>