package caterpillar.exercise.services;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ServiceImpl implements Service {

    private Map<String, Object> memoryCache;
    private Map<String, Object> fileCache;
    private Properties properties;

    public ServiceImpl() {
        memoryCache = new HashMap<>();
        fileCache = new HashMap<>();
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
            try (ObjectInput objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("cache.txt")))) {
                fileCache = (Map<String, Object>) objectInputStream.readObject();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }

            fileCache.put(key, value);

            try (ObjectOutput objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("cache.txt", false)))) {
                objectOutputStream.writeObject(fileCache);
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            return;
        }

        if (key.trim().length() < 1) {
            return;
        }

        memoryCache.put(key, value);
    }
}