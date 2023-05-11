package gp.assessments.chat.utils;

/**
 * Utility class for string-based operations
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isBlank(String str) {
        return str == null || str.isBlank();
    }

}
