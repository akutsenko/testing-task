package common.config;


import org.aeonbits.owner.ConfigCache;

public class ConfigUtil {

    private static CoreConfig properties;

    static {
        properties = ConfigCache.getOrCreate(CoreConfig.class);
    }

    public static CoreConfig getProperties() {
        return properties;
    }

}
