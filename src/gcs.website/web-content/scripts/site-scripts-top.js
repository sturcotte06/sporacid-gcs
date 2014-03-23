/**
 * Site intitialization needed when the DOM is ready
 */
$(document).ready(
	function () {
		// Prevent non-numeric characters from being sent in input
		$("input[type=\"number\"]").keypress(
	    function (event) {
	      var code = event.keyCode || event.which;
	      if (code < 48 || code > 57) {
	        // Non-numeric character, prevent the event
	        return false;
	      }
	    }
		);
		
		// Transform splitters into splitters from jqWidgets
		if ($(".jqw-splitter").length > 0) {
	    $(".jqw-splitter").jqxSplitter({ 
	      theme: cJqWidgetTheme, width: '100%', height: '100%', splitBarSize: 10,
	      panels: [{ size: '20%', min: '15%' }, { size: '80%', min: '65%' }] }
	    );
		}

    // Transform expanders into expanders from jqWidgets
		if ($(".jqw-expander").length > 0) {
		  $(".jqw-expander").jqxExpander({ theme: cJqWidgetTheme });
		}
		
		// Transform menus into menus from jqWidgets
		if ($(".jqw-menu-container").length > 0) {
		  $(".jqw-menu-container").jqxMenu({theme: cJqWidgetTheme});
//		  $(".jqw-menu-container").each(function()  {
//		    var $this = $(this);
//		    $this.on('shown', function () {
//	        $(".jqx-menu-popup").width($this.width());
//	      }); 
//		  });
		  $("#club_selector_item").on("mouseover", function () {
		    $(".jqx-menu-popup").width($(this).outerWidth());
		  }); 
		}
		
    // Attach resize event for full screen user experience (lol buzzword)
    $(window).resize(resizeContent);
	}
);

/**
* Resize the content area of the page according to header, footer and menu current height.
*/
function resizeContent()
{   
  //Set the content to fit the browser's height
  var mainContentHeight = $(window).innerHeight() - $(".status-bar-container").outerHeight();
  $(".main-content").height(mainContentHeight);
  $(".content-container").height($(window).innerHeight());
  // Resize grids
  $(".jq-grid").each(function() {
    var $this = $(this);
    $this.setGridWidth($this.parents(".management-grid-container").width());
    $this.setGridHeight($this.parents(".management-grid-container").height());
  });
}

/**
 * Loads an url response in the main content area using ajax
 * @param url     Url to the resource
 * @param method  Method to use (get or post)
 * @param data    Data to include in the request
 */
function loadContentAjax(url, method, data) {
  method = (method != null ? method.toLowerCase() : "");
  if (method === "get" || method === "post") {
    // Only accept get or post
    if (url != null) {
      $.ajax({
          method: method,
          data: data,
          url: url,
          cache: false,
          success: function (data) {
            $(".main-content").hide().html(data).show();
            $(".main-content .form").each(function () { bindFormHandlers(this); });
          }
      });
    }
  }
}