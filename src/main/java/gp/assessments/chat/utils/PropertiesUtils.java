package gp.assessments.chat.utils;

import java.util.ResourceBundle;

public final class PropertiesUtils {

    private PropertiesUtils() {
    }

    private static final ResourceBundle bundle = ResourceBundle.getBundle("application");

    public static String getAsString(String propertyName) {
        return bundle.getString(propertyName);
    }

    public static int getAsInt(String propertyName) {
        return Integer.parseInt(bundle.getString(propertyName));
    }

}
