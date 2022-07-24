package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigUtils {

    public static Properties readProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            properties.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            inputStream.close();
        } catch (IOException e) {
            throw new IOException("Not found the properties file: " + fileName);
        }
        return properties;
    }

    public static String getEnvValue(String envKey){
        try {
            return System.getenv(envKey);
        } catch (NullPointerException npe) {
            throw new NullPointerException("Cannot find the property key: " + envKey);
        } catch (SecurityException se) {
            throw new SecurityException("Don't have permission to retrieve the env variables.");
        }

    }
}
