$.fn.gcsGrid = function(data) {
	$.jgrid.no_legacy_api = true;
	
	// Assume that data and jQuery selector are ok
	// because there's too much assertion to do
	var random = generateRandomString(10);

	this.id("gcs_grid_container_" + random);
	this.addClass("gcs-grid-container jq-grid-container");

	// Add the header
	var $header = $("<div></div>").id("gcs_grid_menu_" + random).addClass("gcs-grid-menu jq-grid-menu");
	this.append($header);

	// Add the table grid
	var $grid = $("<table></table>").id("gcs_grid_" + random).addClass("gcs-grid jq-grid");
	this.append($grid);

	// Add the pager
	var $pager = $("<div></div>").id("gcs_grid_pager_" + random).addClass("gcs-grid-pager jq-grid-pager");
	this.append($pager);

	$header.gcsMenu(data.menu);

	$grid.jqGrid({
		data : data.rows,
		datatype : "local",
		colNames : data.colNames,
		colModel : data.columns,
		pager : $pager.id(),
		autowidth : true
	});

	$grid.jqGrid("navGrid", "#" + $pager.id(), {
		edit : false,
		add : false,
		del : false
	});
	
	this.find(".ui-icon-refresh, .ui-icon-search").hide();

	// Set the on resize callback
	this.on("resize", onResize);
};

/**
 * Resizes the content of a grid.
 */
function onResize() {
	var $this = $(this);
	var $grid = $this.find(".gcs-grid");
	var $parent = $this.parent();

	var width = $parent.width();
	var height = $parent.height()
				- $this.find(".gcs-grid-menu:first").outerHeight()
				- $this.find(".gcs-grid-pager:first").outerHeight()
				- $this.find(".ui-jqgrid-hdiv:first").outerHeight();
	
	$grid.jqGrid("setGridWidth", width);
	$grid.jqGrid("setGridHeight", height);
	
	return false;
}