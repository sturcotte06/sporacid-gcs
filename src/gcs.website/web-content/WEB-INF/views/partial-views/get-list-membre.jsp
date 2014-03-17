<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
 	String context = (String) request.getContextPath();
 %>
<div class="management-grid-container">
  <div class="management-toolbar">
    <div class="toolbar-button" style="background-image: url(<%=context%>/images/add.png);">
      Ajouter
    </div>
    <div class="toolbar-button" style="background-image: url(<%=context%>/images/edit.png);">
      Modifier
    </div>
    <div class="toolbar-button" style="background-image: url(<%=context%>/images/remove.png);">
      Supprimer
    </div>
    <div class="toolbar-button" style="background-image: url(<%=context%>/images/refresh.png);">
      Rafraichir
    </div>
  </div>
  <div class="management-grid jqw-grid"></div>
</div>

<style type="text/css">
  .management-toolbar {
    height: 30px;
  }
  
  .toolbar-button {
    vertical-align: bottom;
    display: inline-block;
    background-position: left center;
    background-repeat: no-repeat;
    height: 30px;
    padding-left: 20px;
    margin: 0px 10px;
  }

  .management-grid-container {
    -webkit-box-sizing: border-box; /* Safari/Chrome, other WebKit */
    -moz-box-sizing: border-box;    /* Firefox, other Gecko */
    box-sizing: border-box;         /* Opera/IE 8+ */
    height: 100%;
    padding: 50px;
  }
</style>

<script type="text/javascript">
  var data = [{ "empName": "test", "age": "67", "department": { "id": "1234", "name": "Sales" }, "author": "ravi" }];
  
  //prepare the data
  var source =
  {
      datatype: "json",
      datafields: [
          { name: 'empName' },
          { name: 'age' },
          { name: 'id', map: 'department>id' },
          { name: 'name', map: 'department>name' },
          { name: 'author' }
      ],
      localdata: data
  };
  
  var dataAdapter = new $.jqx.dataAdapter(source);

  $(document).ready(
    function () {
      $(".jqw-grid").jqxGrid({ 
        width: "100%",
        height: "100%",
        source: source,
        theme: cJqWidgetTheme,
        columns: [
                  { text: 'Employee Name', datafield: 'empName' },
                  { text: 'Age', datafield: 'age' },
                  { text: 'Dpt Id', datafield: 'id' },
                  { text: 'Dpt Name', datafield: 'name' },
                  { text: 'Author', datafield: 'author' }
              ]
      });
    }
  );
</script>