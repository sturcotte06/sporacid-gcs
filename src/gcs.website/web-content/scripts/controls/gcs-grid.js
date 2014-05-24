$.fn.gcsGrid = function(data) {
  // Assume that data and jQuery selector are ok
  // because there's too much assertion to do  
  var random = generateRandomString(10);
  
  // Add the header
  this.addClass("gcs-grid-container jq-grid-container");
  
  var $header = $("<div></div>")
    .id("gcs_grid_menu_" + random)
    .addClass("gcs-grid-menu jq-grid-menu");
  this.append($header);
  
  // Add the table grid
  var $grid = $("<table></table>")
    .id("gcs_grid_" + random)
    .addClass("gcs-grid jq-grid");
  this.append($grid);
  
  // Add the pager
  var $pager = $("<div></div>")
    .id("gcs_grid_pager_" + random)
    .addClass("gcs-grid-pager jq-grid-pager");
  this.append($pager);
   
  $header.gcsMenu(data.menu);
  
  $grid.jqGrid({
    data: data.rows,
    datatype: "local",
    colNames: data.colNames,
    colModel: data.columns,
    pager: $pager.id(),
    autowidth: true,
  });
  
  $grid.jqGrid("navGrid", "#" + $pager.id(), {
    edit: false, add: false, del: false
  }).find(".ui-icon-refresh, .ui-icon-search").hide();
  
  // Set the on resize callback
  $grid.resize(onResize);
};

/**
 * Resizes the content of a grid.
 */
function onResize() {
    var $this = $(this);
    $this.setGridWidth($this.parents(".management-grid-container:first").outerWidth());
    $this.setGridHeight($this.parents(".management-grid-container:first").innerHeight() -
        $this.parents(".management-grid-container:first").find(".management-grid-header-container:first").outerHeight() -
        $this.parents(".management-grid-container:first").find(".jq-grid-pager:first").outerHeight() - 
        $this.parents(".management-grid-container:first").find(".ui-jqgrid-hdiv:first").outerHeight());
}