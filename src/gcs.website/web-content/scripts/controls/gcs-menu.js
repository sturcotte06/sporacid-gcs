$.fn.gcsMenu = function(data) {
  // Assume that data and jQuery selector are ok
  // because there's too much assertion to do
  
  // Add the header
  this.addClass("gcs-menu-container jq-menu-container");
  var $menu = $("<ul></ul>");
    
  for (var i = 0; i < data.length; i++) {
    var menuItem = data[i];
    
    var $icon = $("<div></div>")
      .addClass("relative")
      .append($("<div></div>")
        .addClass("gcs-menu-icon")
        .css("background-image", "url(" + menuItem.imageUrl + ")"));
        
    var $item = $("<li></li>")
      .append($("<a></a>")
        .text(menuItem.label)
        .attr("href", menuItem.href));
    
    $icon.prependTo($item);
    $item.appendTo($menu);
    // $menu.append($item);

    // Add the submenu. We only accept one level of sub menu.
    if (menuItem.items != undefined) {
      var $subMenu = $("<ul></ul>");
      
      for (var j = 0; j < menuItem.items.length; j++) {
        var subMenuItem = menuItem.items[j];
        var $subIcon = $("<div></div>")
          .addClass("relative")
          .append($("<div></div>")
            .addClass("gcs-grid-menu-icon")
            .css("background-image", "url(" + subMenuItem.imageUrl + ")"));
        
        var $subItem = $("<li></li>")
          .append($subIcon)
          .text(subMenuItem.label);
        
        $subMenu.append($subItem);
      }
      
      $item.append($subMenu);
    }
  }
  
  this.append($menu);
  this.jqxMenu({ theme: cJqWidgetTheme });
};