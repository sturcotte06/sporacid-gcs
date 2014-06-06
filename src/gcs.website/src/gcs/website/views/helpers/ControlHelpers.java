package gcs.website.views.helpers;

import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.website.views.helpers.models.GridColumnModel;
import gcs.website.views.helpers.models.Menu;
import gcs.website.views.helpers.models.MenuItem;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;

import com.google.gson.Gson;

/**
 * @author Simon Turcotte-Langevin
 */
public final class ControlHelpers
{
    /**
     * Json serializer for javascript conversion.
     */
    private static final Gson jsonSerializer = new Gson();

    /**
     * Serialize an object into hidden field inputs.
     * 
     * @param object An object to serialize
     * @param classObj The class of the object
     * @param propertyName Name of the property
     * @return An html string with hidden inputs for every properties of the
     *         object
     */
    public static <E> HtmlString serializeToHiddenFields(E object, Class<E> classObj, String propertyName)
    {
        StringBuffer out = new StringBuffer();

        Map<String, Object> objProperties = null;

        // Get the object properties through reflection
        objProperties = ReflectionUtils.getObjectProperties(object, classObj);

        // For each object properties
        for (Map.Entry<String, Object> entry : objProperties.entrySet()) {
            // Modify the key with the property name in parameter
            String modifiedKey = propertyName + "." + entry.getKey();
            // We want the value as a string
            String strValue = entry.getValue().toString();
            out.append("<input type=\"hidden\" name=\"" + modifiedKey + "\" value=\"" + strValue + "\" />\n");
        }

        return new HtmlString(out.toString());
    }

    public static <E> HtmlAndJavaScript getGridForObjects(Collection<E> objects, Class<E> classObj, Menu menu)
    {
        String gridId = "gcs_grid_container_" + RandomStringUtils.random(10, true, true);

        StringBuilder htmlOut = new StringBuilder();
        htmlOut.append(String.format("<div id=\"%s\">", gridId));
        // htmlOut.append(String.format("<div></div>"));
        // htmlOut.append(String.format("<table></table>"));
        // htmlOut.append(String.format("<div></div>"));
        htmlOut.append(String.format("<div>"));

        StringBuilder menuOut = new StringBuilder();
        menuOut.append("[");
        for (MenuItem item : menu.getItems()) {
            menuOut.append(String.format("{label: \"%s\", imageUrl: \"%s\", href: \"%s\"},", item.getLabel(),
                    item.getImageUrl(), item.getHref()));
        }

        // Strip the last char
        int lastIndex = menuOut.length() - 1;
        if (menuOut.charAt(lastIndex) == ',') {
            menuOut.deleteCharAt(lastIndex);
        }

        menuOut.append("]");

        Collection<GridColumnModel> columns = new ArrayList<>();
        StringBuilder columnsDisplayOut = new StringBuilder();
        columnsDisplayOut.append("[");

        Field[] fields = classObj.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                // Only iterate through non-static fields
                continue;
            }

            Display display = field.getAnnotation(Display.class);
            columnsDisplayOut.append(String.format("\"%s\",", display != null
                    ? display.value()
                    : field.getName()));

            GridColumnModel column = new GridColumnModel();
            column.setName(field.getName());
            column.setIndex(field.getName());
            column.setWidth(String.valueOf(100f / fields.length) + "%");

            if (field.getType().isAssignableFrom(Float.class)) {
                column.setSorttype("number");
                column.setAlign("right");
            } else if (field.getType().isAssignableFrom(Byte.class)) {
                column.setSorttype("int");
                column.setAlign("right");
            } else if (field.getType().isAssignableFrom(Date.class)) {
                column.setSorttype("date");
                column.setAlign("right");
            } else {
                column.setSorttype("string");
                column.setAlign("left");
            }

            columns.add(column);
        }

        // Strip the last char
        lastIndex = columnsDisplayOut.length() - 1;
        if (columnsDisplayOut.charAt(lastIndex) == ',') {
            columnsDisplayOut.deleteCharAt(lastIndex);
        }

        columnsDisplayOut.append("]");

        // Generate the json data from the collection
        String rowsJson = jsonSerializer.toJson(objects);
        String columnsJson = jsonSerializer.toJson(columns);

        StringBuilder jsOut = new StringBuilder();
        jsOut.append(String.format("var initData = {menu: %s, rows: %s, columns: %s, colNames: %s};",
                menuOut.toString(), rowsJson, columnsJson, columnsDisplayOut.toString()));
        jsOut.append(String.format("$(\"#%s\").gcsGrid(initData);", gridId));

        return new HtmlAndJavaScript(htmlOut.toString(), jsOut.toString());
    }

    /**
     * @param objects
     * @param classObj
     * @return
     */
    /* public static <E> HtmlAndJavaScript getGridForObjects(Collection<E>
     * objects, Class<E> classObj, String cssClass) { String randomStr =
     * RandomStringUtils.random(12, true, true); String gridId = "gcs_grid_" +
     * randomStr; String pagerId = "gcs_grid_pager_" + randomStr;
     * 
     * StringBuilder htmlOut = new StringBuilder();
     * htmlOut.append(String.format("<table id=\"%s\" class=\"jq-grid %s\">",
     * gridId, cssClass)); htmlOut.append(String.format("</table>"));
     * htmlOut.append(String.format("<div id=\"%s\" class=\"jq-grid-pager\">",
     * pagerId)); htmlOut.append(String.format("</div>"));
     * 
     * Collection<GridColumnModel> columns = new ArrayList<>(); StringBuilder
     * columnsDisplayOut = new StringBuilder(); columnsDisplayOut.append("[");
     * 
     * Field[] fields = classObj.getDeclaredFields(); for (Field field : fields)
     * { Display display = field.getAnnotation(Display.class);
     * columnsDisplayOut.append(String.format("\"%s\",", display != null ?
     * display.value() : field.getName()));
     * 
     * GridColumnModel column = new GridColumnModel();
     * column.setName(field.getName()); column.setIndex(field.getName());
     * column.setWidth(String.valueOf(100f / fields.length) + "%");
     * 
     * if (field.getType().isAssignableFrom(Float.class)) {
     * column.setSorttype("number"); column.setAlign("right"); } else if
     * (field.getType().isAssignableFrom(Byte.class)) {
     * column.setSorttype("int"); column.setAlign("right"); } else if
     * (field.getType().isAssignableFrom(Date.class)) {
     * column.setSorttype("date"); column.setAlign("right"); } else {
     * column.setSorttype("string"); column.setAlign("left"); }
     * 
     * columns.add(column); }
     * 
     * // Strip the last char int lastIndex = columnsDisplayOut.length() - 1; if
     * (columnsDisplayOut.charAt(lastIndex) == ',') {
     * columnsDisplayOut.deleteCharAt(lastIndex); }
     * 
     * columnsDisplayOut.append("]");
     * 
     * // Generate the json data from the collection String data =
     * jsonSerializer.toJson(objects); String colData =
     * jsonSerializer.toJson(columns);
     * 
     * StringBuilder jsOut = new StringBuilder();
     * 
     * //jsOut.append(String.format("jQuery(document).ready(function () { "));
     * jsOut.append(String.format("  var data = %s;", data));
     * jsOut.append(String.format("  $(\"#%s\").jqGrid({", gridId));
     * jsOut.append(String.format("    data: data, "));
     * jsOut.append(String.format("    datatype: \"local\", "));
     * jsOut.append(String.format("    colNames: %s, ",
     * columnsDisplayOut.toString()));
     * jsOut.append(String.format("    colModel: %s, ", colData));
     * jsOut.append(String.format("    pager: \"#%s\",", pagerId));
     * jsOut.append(String.format("    autowidth: true"));
     * jsOut.append(String.format("  });"));
     * jsOut.append(String.format("  $(\"#%s\").jqGrid(\"navGrid\", \"#%s\", {",
     * gridId, pagerId)); jsOut.append(String.format("    edit: false,"));
     * jsOut.append(String.format("    add: false,"));
     * jsOut.append(String.format("    del: false"));
     * jsOut.append(String.format("  });"));
     * //jsOut.append(String.format("});"));
     * 
     * return new HtmlAndJavaScript(htmlOut.toString(), jsOut.toString()); } */
}
