$.fn.gcsMainContent = function(data) {
	// Assume that data and jQuery selector are ok
	// because there's too much assertion to do
	var random = generateRandomString(10);

	this.id("gcs_main_content_container_" + random);
	this.attr("style", "height: 100%; width: 100%;");
	var isHorizontal = data.orientation.toLowerCase() === "horizontal";

	if (isHorizontal) {
		// Create the first row
		var $firstRow = $("<div></div>").id("gcs_main_content_first_row_" + random).attr("style", "height: 50%;")
				.addClass("main-content-vertical-row vertical-first-row row");

		// Create the second row
		var $secondRow = $("<div></div>").id("gcs_main_content_second_row_" + random).attr("style", "height: 50%;")
				.addClass("main-content-vertical-row vertical-second-row row");

		// Get all contents
		var $firstContent = this.find(".main-content-1");
		var $secondContent = this.find(".main-content-2");

		// Add the contents to the right row
		$firstRow.prepend($firstContent);
		$secondRow.prepend($secondContent);

		// Append the rows to this
		this.prepend($secondRow);
		this.prepend($firstRow);
	} else {
		this.addClass("row");
		
		// Create the left column
		var $leftCol = $("<div></div>").id("gcs_main_content_left_col_" + random).attr("style", "height: 100%;").addClass(
				"main-content-horizontal-col horizontal-col-left col-md-6");

		// Create the right column
		var $rightCol = $("<div></div>").id("gcs_main_content_right_col_" + random).attr("style", "height: 100%;").addClass(
				"main-content-horizontal-col horizontal-col-right col-md-6");

		// Get all contents
		var $leftContent = this.find(".main-content-1");
		var $rightContent = this.find(".main-content-2");

		// Add the contents to the right column
		$leftCol.prepend($leftContent);
		$rightCol.prepend($rightContent);

		// Append the columns to this
		this.prepend($rightCol);
		this.prepend($leftCol);
	}
};
