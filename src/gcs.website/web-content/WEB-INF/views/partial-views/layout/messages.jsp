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
    <li class="message <%=(message.getMessageType() == MessageType.Information ? "success" : "failure")%>">
      <%=message.getMessageContent() %>
    </li>
  <% } %>
</ul>        
<% SessionUtils.clearMessages(httpSession); %>