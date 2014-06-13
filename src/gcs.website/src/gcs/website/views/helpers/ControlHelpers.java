package gcs.website.views.helpers;

import gcs.webapp.utils.Display;
import gcs.webapp.utils.reflect.ReflectionUtils;
import gcs.website.views.helpers.models.GridColumnModel;
import gcs.website.views.helpers.models.Menu;
import gcs.website.views.helpers.models.MenuItem;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
        objProperties = ReflectionUtils.flatten(object);

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
    	String uniqueId = RandomStringUtils.random(10, true, true);
        String gridId = "gcs_grid_container_" + uniqueId;

        StringBuilder htmlOut = new StringBuilder(String.format("<div id=\"%s\"></div>", gridId));
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

        // Resolve the grid column metadata in a bizarre separate method.
        // Technically, this shouldn't be in a method, but it's recursive.
        resolveGridColumnMetadata(classObj, columnsDisplayOut, columns);

        // Strip the last char
        lastIndex = columnsDisplayOut.length() - 1;
        if (columnsDisplayOut.charAt(lastIndex) == ',') {
            columnsDisplayOut.deleteCharAt(lastIndex);
        }

        columnsDisplayOut.append("]");

        // Generate the json data from the collection
        Collection<Map<String,Object>> flattenedObjects = new ArrayList<>();
        for(Object obj : objects)
        {
        	flattenedObjects.add(ReflectionUtils.flatten(classObj.cast(obj)));
        }
        
        String rowsJson = jsonSerializer.toJson(flattenedObjects);
        String columnsJson = jsonSerializer.toJson(columns);

        StringBuilder jsOut = new StringBuilder();
        jsOut.append(String.format("var initData_%s = {menu: %s, rows: %s, columns: %s, colNames: %s};",
        		uniqueId, menuOut.toString(), rowsJson, columnsJson, columnsDisplayOut.toString()));
        jsOut.append(String.format("$(\"#%s\").gcsGrid(initData_%s);", gridId, uniqueId));

        return new HtmlAndJavaScript(htmlOut.toString(), jsOut.toString());
    }

    private static <E> void resolveGridColumnMetadata(Class<E> classObj, StringBuilder columnsDisplayOut,
            Collection<GridColumnModel> columns)
    {
        Field[] fields = classObj.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                // Only iterate through non-static fields
                continue;
            }

            Class<?> fieldType = field.getType();
            if (fieldType.isArray() || Map.class.isAssignableFrom(fieldType)
                    || Collection.class.isAssignableFrom(fieldType)) {
                // We don't deal with inner collections and sub tables. Yet.
                continue;
            }

            if (!ReflectionUtils.isPrimitive(fieldType)) {
                // Recursivity
                resolveGridColumnMetadata(fieldType, columnsDisplayOut, columns);
            }

            Display display = field.getAnnotation(Display.class);
            if (display == null) {
                // The field is not meant to be displayed
                continue;
            }

            // Add the header of the field to the columns display string
            // builder.
            columnsDisplayOut.append(String.format("\"%s\",", display.header()));

            // Add the actual
            GridColumnModel column = new GridColumnModel();
            column.setName(field.getName());
            column.setIndex(field.getName());
            column.setWidth(display.width() + "px");

            if (Float.class.isAssignableFrom(field.getType())) {
                column.setSorttype("number");
                column.setAlign("right");
            } else if (Byte.class.isAssignableFrom(field.getType())) {
                column.setSorttype("int");
                column.setAlign("right");
            } else if (Date.class.isAssignableFrom(field.getType())) {
                column.setSorttype("date");
                column.setAlign("right");
            } else {
                column.setSorttype("string");
                column.setAlign("left");
            }

            columns.add(column);
        }
    }

    // public static <E> HtmlAndJavaScript getGridForObjects(Collection<E>
    // objects, Class<E> classObj, Menu menu)
    // {
    // String gridId = "gcs_grid_container_" + RandomStringUtils.random(10,
    // true, true);
    //
    // StringBuilder htmlOut = new
    // StringBuilder(String.format("<div id=\"%s\"></div>", gridId));
    // StringBuilder menuOut = new StringBuilder();
    // menuOut.append("[");
    // for (MenuItem item : menu.getItems()) {
    // menuOut.append(String.format("{label: \"%s\", imageUrl: \"%s\", href: \"%s\"},",
    // item.getLabel(),
    // item.getImageUrl(), item.getHref()));
    // }
    //
    // // Strip the last char
    // int lastIndex = menuOut.length() - 1;
    // if (menuOut.charAt(lastIndex) == ',') {
    // menuOut.deleteCharAt(lastIndex);
    // }
    //
    // menuOut.append("]");
    //
    // Collection<GridColumnModel> columns = new ArrayList<>();
    // StringBuilder columnsDisplayOut = new StringBuilder();
    // columnsDisplayOut.append("[");
    //
    // Field[] fields = classObj.getDeclaredFields();
    // for (Field field : fields) {
    // if (Modifier.isStatic(field.getModifiers())) {
    // // Only iterate through non-static fields
    // continue;
    // }
    //
    // Display display = field.getAnnotation(Display.class);
    // columnsDisplayOut.append(String.format("\"%s\",", display != null
    // ? display.value()
    // : field.getName()));
    //
    // GridColumnModel column = new GridColumnModel();
    // column.setName(field.getName());
    // column.setIndex(field.getName());
    // column.setWidth(String.valueOf(100f / fields.length) + "%");
    //
    // if (field.getType().isAssignableFrom(Float.class)) {
    // column.setSorttype("number");
    // column.setAlign("right");
    // } else if (field.getType().isAssignableFrom(Byte.class)) {
    // column.setSorttype("int");
    // column.setAlign("right");
    // } else if (field.getType().isAssignableFrom(Date.class)) {
    // column.setSorttype("date");
    // column.setAlign("right");
    // } else {
    // column.setSorttype("string");
    // column.setAlign("left");
    // }
    //
    // columns.add(column);
    // }
    //
    // // Strip the last char
    // lastIndex = columnsDisplayOut.length() - 1;
    // if (columnsDisplayOut.charAt(lastIndex) == ',') {
    // columnsDisplayOut.deleteCharAt(lastIndex);
    // }
    //
    // columnsDisplayOut.append("]");
    //
    // // Generate the json data from the collection
    // String rowsJson = jsonSerializer.toJson(objects);
    // String columnsJson = jsonSerializer.toJson(columns);
    //
    // StringBuilder jsOut = new StringBuilder();
    // jsOut.append(String.format("var initData = {menu: %s, rows: %s, columns: %s, colNames: %s};",
    // menuOut.toString(), rowsJson, columnsJson,
    // columnsDisplayOut.toString()));
    // jsOut.append(String.format("$(\"#%s\").gcsGrid(initData);", gridId));
    //
    // return new HtmlAndJavaScript(htmlOut.toString(), jsOut.toString());
    // }

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
