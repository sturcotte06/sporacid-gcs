<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
  String context = (String) request.getContextPath();
%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestionnaire de club scientifique</title>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-top.jsp"></jsp:include>
    
  </head>
  <body class="unselectable">    
  
    <div class="content-container jqx-splitter">
      <div class="content-pane jqx-splitter-pane">
      
        <%-- Include the menu in the page --%>
        <jsp:include page="partial-views/layout/menu.jsp"></jsp:include>
        
      </div>
      <div class="content-pane jqx-splitter-pane">
      
        <%-- Include the menu in the page --%>
        <jsp:include page="partial-views/layout/status-bar.jsp"></jsp:include>
        
        <div class="main-content">
          
          <%-- Include the menu in the page --%>
          <jsp:include page="partial-views/layout/messages.jsp"></jsp:include>
          
        </div>
      </div>
    </div>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-bottom.jsp"></jsp:include>
    
  </body>
</html>