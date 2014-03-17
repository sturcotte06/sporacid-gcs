/**
 * Document ready for forms within the application 
 */
$(document).ready(
	function () {
	  $(".form").each(function () { bindFormHandlers(this); });
	}
);

/**
 * 
 * @param form an html html element with the class form
 */
function bindFormHandlers(form) {
  var $form = $(form);
  if ($form.hasClass("form")) {
    // Simulate the submit button behaviour on the form submit control
    $form.find(".form-submit").on("click",
      function (event) {
        $(this).parents("form").submit();
      }
    );
    
    // Handle key presses in the form
    $form.not(".form-text-area").on("keypress",
      function (event) {
        // If enter was pressed, submit the form
        if (event.keyCode == 13) {
          $(this).submit();
        }
      } 
    );
    
    // Disable read only inputs
    $form.find(".form-input.read-only").attr("disabled", "disabled");
  }
}
