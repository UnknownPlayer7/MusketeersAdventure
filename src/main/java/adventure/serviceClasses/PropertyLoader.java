package adventure.serviceClasses;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    @Getter
    private Properties properties;

    public PropertyLoader(String path) {
        properties = new Properties();

        try(InputStream io = PropertyLoader.class.getResourceAsStream(path)){
            properties.load(io);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

}
