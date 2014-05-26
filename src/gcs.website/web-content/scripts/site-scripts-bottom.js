/**
 * Site intitialization needed when the DOM is ready
 */
$(document).ready(
	function () {
		$(window).resize();
		
		
		
    // Transform dropdownlists into dropdownlists from jqWidgets
	// $(".jqw-dropdownlist").each(
	// function () {
	// var $this = $(this);
	// var source = $this.data(cJqWidgetDataSourceKey);
	//        
	// $this.jqxDropDownList({
	// theme: cJqWidgetTheme, //"";
	// source: source, selectedIndex: 0,
	// valueMember: "id", displayMember: "value"
	// });
	//        
	// // Fixes a bug with padding in jqWidgets ddl
	// $this.on("open",
	// function (event) {
	// var $this = $(this);
	// $this.jqxDropDownList({ dropDownWidth: $this.innerWidth() });
	// }
	// );
	//        
	//      }
	//    );
	}
);