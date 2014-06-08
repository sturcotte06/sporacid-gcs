<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="gcs.webapp.utils.Message" %>
<%@page import="gcs.webapp.utils.MessageType" %>
<%@page import="gcs.website.utils.SessionUtils" %>

<%
  HttpSession httpSession = request.getSession();
  String context = (String) request.getContextPath();
%>

<ul class="messages">
  <% for (Message message : SessionUtils.getMessages(httpSession)) { %>
    <% 
      MessageType mt = message.getType();
      String classAttr = null;
      switch (message.getType()) {
          case Information:
              classAttr = "success";
              break;
          case Validation:
          case Warning:
              classAttr = "warning";
              break;
          case Error:
              classAttr = "danger";
              break;
      }
    %>
    <li class="message <%=classAttr%>">
      <%=message.getContent() %>
    </li>
  <% } %>
</ul>

<% SessionUtils.clearMessages(httpSession); %>