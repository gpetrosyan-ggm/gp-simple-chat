package gp.assessments.chat.utils;

import java.util.ResourceBundle;

/**
 * Utility class for reading properties from the resources
 */
public final class PropertiesUtils {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    private PropertiesUtils() {
    }

    public static String getAsString(String propertyName) {
        return bundle.getString(propertyName);
    }

    public static int getAsInt(String propertyName) {
        return Integer.parseInt(bundle.getString(propertyName));
    }

    public static boolean getAsBoolean(String propertyName) {
        return Boolean.getBoolean(bundle.getString(propertyName));
    }

}
