package gcs.website.views.helpers;

import static org.reflections.ReflectionUtils.*;
import static gcs.webapp.utils.reflect.ReflectionsExtension.*;
import gcs.webapp.utils.Display;
import gcs.webapp.utils.app.messages.IMessageLocalizer;
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
import java.util.Set;

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
     * 
     */
    private static IMessageLocalizer messageLocalizer;

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
        StringBuilder out = new StringBuilder();

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

    /**
     * @param objects
     * @param classObj
     * @param menu
     * @return
     */
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
        Collection<Map<String, Object>> flattenedObjects = new ArrayList<>();
        for (Object obj : objects) {
            flattenedObjects.add(ReflectionUtils.flatten(classObj.cast(obj)));
        }

        String rowsJson = jsonSerializer.toJson(flattenedObjects);
        String columnsJson = jsonSerializer.toJson(columns);

        StringBuilder jsOut = new StringBuilder();
        jsOut.append(
                String.format("var initData_%s = {menu: %s, rows: %s, columns: %s, colNames: %s};", uniqueId,
                        menuOut.toString(), rowsJson, columnsJson, columnsDisplayOut.toString())).append("\n");
        jsOut.append(String.format("$(\"#%s\").gcsGrid(initData_%s);\n", gridId, uniqueId)).append("\n");

        return new HtmlAndJavaScript(htmlOut.toString(), jsOut.toString());
    }

    /**
     * @param classObj
     * @param columnsDisplayOut
     * @param columns
     */
    @SuppressWarnings("unchecked")
    private static <E> void resolveGridColumnMetadata(Class<E> classObj, StringBuilder columnsDisplayOut,
            Collection<GridColumnModel> columns)
    {

        final Set<Field> fields = getAllFields(classObj,
        // We want non static fields.
                not(withModifier(Modifier.STATIC)),
                // We want non final fields.
                not(withModifier(Modifier.FINAL)),
                // We want only fields meant to be displayed
                or(withAnnotation(Display.class),
                // or resolvable sub-beans
                        not(or(isPrimitive(), withType(String.class), withType(Date.class)))),
                // We don't deal with inner collections and sub tables.
                not(or(withTypeAssignableTo(Map.class), withTypeAssignableTo(Collection.class), isArray())));

        for (Field field : fields) {
            Display display = field.getAnnotation(Display.class);
            Class<?> fieldType = field.getType();

            if (!ReflectionUtils.isPrimitive(fieldType) && !String.class.isAssignableFrom(fieldType)
                    && !Date.class.isAssignableFrom(fieldType)) {
                // Field is an object.
                // Recursivity.
                resolveGridColumnMetadata(fieldType, columnsDisplayOut, columns);
                continue;
            }

            // Add the header of the field to the columns display string
            // builder.
            columnsDisplayOut.append(String.format("\"%s\",", messageLocalizer.localizeString(display.header())));

            // Add the actual column.
            GridColumnModel column = new GridColumnModel();
            column.setName(field.getName());
            column.setIndex(field.getName());
            column.setWidth(display.width() + "px");

            if (Float.class.isAssignableFrom(fieldType)) {
                column.setSorttype("number");
                column.setAlign("right");
            } else if (Byte.class.isAssignableFrom(fieldType)) {
                column.setSorttype("int");
                column.setAlign("right");
            } else if (Date.class.isAssignableFrom(fieldType)) {
                column.setSorttype("date");
                column.setAlign("right");
            } else {
                column.setSorttype("string");
                column.setAlign("left");
            }

            columns.add(column);
        }
    }

    /**
     * @return the messageLocalizer
     */
    public static IMessageLocalizer getMessageLocalizer()
    {
        return messageLocalizer;
    }

    /**
     * @param messageLocalizer the messageLocalizer to set
     */
    public static void setMessageLocalizer(IMessageLocalizer messageLocalizer)
    {
        ControlHelpers.messageLocalizer = messageLocalizer;
    }
}
