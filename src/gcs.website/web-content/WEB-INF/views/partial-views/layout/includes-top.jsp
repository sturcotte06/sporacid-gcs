<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
  String context = (String) request.getContextPath();
%>

<!-- Put all javascripts imports here -->
<script type="text/javascript" src="<%=context%>/scripts/libraries/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/libraries/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/libraries/jqgrid/js/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/libraries/jqwidgets/jqx-all.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/libraries/jquery-ui/js/jquery-ui-1.10.4.custom.min.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/libraries/bootstrap-3.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/constants.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/controls/gcs-grid.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/controls/gcs-menu.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/form.js"></script>
<script type="text/javascript" src="<%=context%>/scripts/site-scripts-top.js"></script>

<!-- Put all css imports here -->
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/bootstrap-3.1.1/bootstrap.metrodark.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/bootstrap-3.1.1/css/bootstrap.min.css" /> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/bootstrap-3.1.1/css/bootstrap-theme.min.css" /> --%>
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/jquery-ui/css/metrodark/jquery-ui-1.10.4.custom.min.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/jqgrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/jqwidgets/styles/jqx.base.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/jqwidgets/styles/jqx.shinyblack.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/scripts/libraries/jqwidgets/styles/jqx.metrodark.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/styles/site.css" />
<link rel="stylesheet" type="text/css" href="<%=context%>/styles/themes/metro-dark.css" />