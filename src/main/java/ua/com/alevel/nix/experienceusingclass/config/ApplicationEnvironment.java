package ua.com.alevel.nix.experienceusingclass.config;

import java.util.HashMap;
import java.util.Map;

public class ApplicationEnvironment {

    private static final String LANG_KEY = "lang";

    private static final Map<String, String> APPLICATION_ENVIRONMENT_PROPERTIES = new HashMap<>();

    public static void setPropertyLang(String lang) {
        APPLICATION_ENVIRONMENT_PROPERTIES.put(LANG_KEY, lang);
    }

    public static String getPropertyLang() {
        return APPLICATION_ENVIRONMENT_PROPERTIES.get(LANG_KEY);
    }
}
