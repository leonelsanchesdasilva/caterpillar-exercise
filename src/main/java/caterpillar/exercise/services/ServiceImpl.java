package caterpillar.exercise.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceImpl implements Service {

    private Map<String, Object> memoryCache;
    private Properties properties;

    public ServiceImpl() {
        memoryCache = new HashMap<>();
        properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Object get(String key) {
        if (memoryCache.containsKey(key)) {
            return memoryCache.get(key);
        }
        return null;
    }
    public void put(String key, Object value) {
        if (Integer.parseInt(properties.getProperty("cacheSize")) <= memoryCache.size()) {
            System.out.println("Cache is full");
            return;
        }

        if (key.trim().length() < 1) {
            return;
        }

        memoryCache.put(key, value);
    }
}