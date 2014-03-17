<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
  String context = (String) request.getContextPath();
%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-top.jsp"></jsp:include>
    
  </head>
  <body>
  
    <%-- Include the header in the page --%>
    <jsp:include page="partial-views/layout/header.jsp"></jsp:include>
    
    <%-- Include the menu in the page --%>
    <jsp:include page="partial-views/layout/menu.jsp"></jsp:include>
  
    <div class="content-container">
    
      <div class="main-content">
        <div class="header1"></div>
        
        <%-- Include the menu in the page --%>
        <jsp:include page="partial-views/layout/messages.jsp"></jsp:include>
        
      </div>
      
    </div>
    
    <%-- Include the right column in the page --%>
    <jsp:include page="partial-views/layout/footer.jsp"></jsp:include>
    
    <%-- Include common scripts and styles in the page --%>
    <jsp:include page="partial-views/layout/includes-bottom.jsp"></jsp:include>
    
  </body>
</html>