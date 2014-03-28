<%@page import="gcs.webapp.utils.app.menus.SubMenuItem"%>
<%@page import="gcs.webapp.utils.app.menus.SubMenu"%>
<%@page import="gcs.webapp.utils.app.menus.MainMenuItem"%>
<%@page import="gcs.website.utils.SessionUtils"%>
<%@page import="gcs.webapp.utils.app.menus.MainMenu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
   String context = (String) request.getContextPath();
   MainMenu applicationMenu = SessionUtils.getApplicationMenu(request.getSession());
%>
<div class="menu-container unselectable">
  <%
     if (applicationMenu != null) {
  %>
    <ul class="menu">
      <%
         for (MainMenuItem item : applicationMenu.getItems()) {
      %>
        <% SubMenu submenu = item.getSubmenu(); %>
        <li class="menu-item">
          <div class="menu-item-expander<%=(submenu != null ? " jqw-expander" : "") %>">
            <div class="menu-item-header">
              <% if (item.getPath() != null && !item.getPath().equals("")) { %>
                <a class="menu-item-link" href="javascript:loadContentAjax('<%=context + item.getPath()%>', 'get');"><%=item.getName()%></a>
              <% } else { %>
                <%=item.getName()%>
              <% } %>
            </div>
            <% if (submenu != null) { %>
              <div class="menu-item-content">
                <ul class="sub-menu">
                  <% for (SubMenuItem submenuItem : submenu.getItems()) { %>
                    <li class="sub-menu-item">
                      <a class="sub-menu-item-link" href="javascript:loadContentAjax('<%=context + submenuItem.getPath()%>', 'get');">
                        <%=submenuItem.getName()%>
                      </a>
                    </li>
                  <% } %>
                </ul>
              </div>
          </div>
          <% } %>
        </li>
      <% } %>
    </ul>
  <% } %>

<!--   <ul class="menu"> -->
<!--   	<li class="menu-item"> -->
<!--       <div class="menu-item-expander jqw-expander"> -->
<!--         <div class="menu-item-header"> -->
<!--           Membres -->
<!--         </div> -->
<!--         <div class="menu-item-content"> -->
<!--           <ul class="sub-menu"> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/membres/gerer">Gestion des membres</a> --%>
<!--             </li> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/membres/ajouter">Ajouter un membre</a> --%>
<!--             </li> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/membres/ajouter-formation">Gestion des formations</a> --%>
<!--             </li> -->
<!--           </ul> -->
<!--         </div> -->
<!--       </div> -->
<!--   	</li> -->
<!--     <li class="menu-item"> -->
<!--       <div class="menu-item-expander jqw-expander"> -->
<!--         <div class="menu-item-header"> -->
<!--           Inventaire -->
<!--         </div> -->
<!--         <div class="menu-item-content"> -->
<!--           <ul class="sub-menu"> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/inventaire/gerer">Gestion de l'inventaire</a> --%>
<!--             </li> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/inventaire/ajouter">Ajouter un item</a> --%>
<!--             </li> -->
<!--           </ul> -->
<!--         </div> -->
<!--       </div>  -->
<!--     </li> -->
<!--     <li class="menu-item"> -->
<!--       <div class="menu-item-expander jqw-expander"> -->
<!--         <div class="menu-item-header"> -->
<!--           Fournisseurs -->
<!--         </div> -->
<!--         <div class="menu-item-content"> -->
<!--           <ul class="sub-menu"> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/fournisseurs/gerer">Gestion des fournisseurs</a> --%>
<!--             </li> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/fournisseurs/ajouter">Ajouter un fournisseur</a> --%>
<!--             </li> -->
<!--           </ul> -->
<!--         </div> -->
<!--       </div> -->
<!--     </li> -->
<!--     <li class="menu-item"> -->
<!--       <div class="menu-item-expander jqw-expander"> -->
<!--         <div class="menu-item-header"> -->
<!--           Commandites -->
<!--         </div> -->
<!--         <div class="menu-item-content"> -->
<!--           <ul class="sub-menu"> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/commandites/gerer">Gestion des commandites</a> --%>
<!--             </li> -->
<!--             <li class="sub-menu-item"> -->
<%--               <a class="sub-menu-item-link" href="<%=context%>/commandites/ajouter">Ajouter une commandite</a> --%>
<!--             </li> -->
<!--           </ul> -->
<!--         </div> -->
<!--       </div> -->
<!--     </li> -->
<!--     <li class="menu-item"> -->
<!--       <div class="menu-item-header"> -->
<%--         <a class="menu-item-link" href="<%=context%>/accueil/aide">Aide</a> --%>
<!--       </div> -->
<!--     </li> -->
<!--   </ul> -->
    
  <%-- Include the footer in the menu --%>
  <jsp:include page="footer.jsp"></jsp:include>
  
</div>