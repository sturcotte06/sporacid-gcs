/**
 * Site intitialization needed when the DOM is ready
 */
$(document).ready(function () {
	// Prevent non-numeric characters from being sent in input
	$("input[type=\"number\"]").keypress(function (event) {
		var code = event.keyCode || event.which;
		if (code < 48 || code > 57) {
			// Non-numeric character, prevent the event
			return false;
		}
    });
	
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
	}
	
    $("#club_selector_item").on("mouseover", function () {
    	$(".jqx-menu-popup").width($(this).outerWidth());
    }); 
		
    // Attach resize event for full screen user experience (lol buzzword)
    $(window).resize(resizeContent);
});

/**
* Resize the content area of the page according to header, footer and menu current height.
*/
function resizeContent() {
	var windowHeight = $(window).innerHeight();
	var mainContentHeight = windowHeight - $(".status-bar-container").outerHeight();

	// Set the content to fit the browser's height
	$(".main-content").height(mainContentHeight);
	$(".content-container").height(windowHeight);

	// Resize grids
	$(".gcs-grid-container").resize();
	
	
  
  // Resize grids
  /*$(".jq-grid").resize();
  $(".jq-grid").each(function() {
    var $this = $(this);
    $this.setGridWidth($this.parents(".management-grid-container:first").outerWidth());
    $this.setGridHeight($this.parents(".management-grid-container:first").innerHeight() -
        $this.parents(".management-grid-container:first").find(".management-grid-header-container:first").outerHeight() -
        $this.parents(".management-grid-container:first").find(".jq-grid-pager:first").outerHeight() - 
        $this.parents(".management-grid-container:first").find(".ui-jqgrid-hdiv:first").outerHeight());
  });*/
}

/**
 * Generates a random string of n alphanumeric characters.
 * @param length Length of the string to generate
 */
function generateRandomString(length) {
	var text = "";
	var characterPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	for (var i = 0; i < length; i++) {
		text += characterPool.charAt(Math.floor(Math.random() * characterPool.length));
	}

	return text;
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
				method : method,
				data : data,
				dataType : "html",
				url : url,
				cache : false,
				success : function(data) {
//					var $data = $(data);
//					if ($data.is("html")) {
//						// Trying to load a full page as a partial page.
//						var errorPage = $("<div/>").text("Couldn't load page " + url + " as a partial page.");
//						$(".main-content").hide().html(errorPage).show();
//					} else {
//						// Load the partial page
//						$(".main-content").hide().html(data).show();
//						$(".main-content .form").each(function() {
//							bindFormHandlers(this);
//						});
//					}
					
					// Load the partial page
					$(".main-content").hide().html(data).show();
					$(".main-content .form").each(function() {
						bindFormHandlers(this);
					});

					// Trigger the resize
					$(window).resize();
				}
			});
		}
	}
}

/**
 * Returns whether the jQuery element has any children matching the selector.
 * 
 * @param selector
 *            The selector for children
 */
jQuery.fn.exists = function(selector) {
	return this.find(selector).length > 0;
};

/**
 * Returns whether the jQuery element has any element.
 */
jQuery.fn.exists = function() {
	return this.length > 0;
};

/**
 * Get or set the id of the element.
 */
jQuery.fn.id = function(id) {
	if (id == undefined) {
		return this.attr("id");
	} else {
		return this.attr("id", id);
	}
};