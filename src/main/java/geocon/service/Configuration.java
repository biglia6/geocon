package geocon.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public static String PRIMARY_INDEX_NAME;
    public static String USERS_TYPE_NAME;
    public static String EVENTS_TYPE_NAME;
    public static String RESOURCES_TYPE_NAME;
    public static String PLACES_TYPE_NAME;
    public static String ADDRESS;
    public static String PORT;
    public static String SERVICE_NAME;
    public static String CLUSTER_NAME;
    public static String CLUSTER_PORT;
    public static String CLUSTER_ADDRESS;

    // Private constructor to prevent initialization.
    private Configuration() {
    }

    // reading from config.properites
    static {
        // dumb init
        Properties prop = null;
        try {
            FileInputStream input = new FileInputStream("config.properties");
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CLUSTER_PORT = prop.getProperty("cluster_port");
        CLUSTER_ADDRESS = prop.getProperty("cluster_address");
        CLUSTER_NAME = prop.getProperty("cluster_name");
        PRIMARY_INDEX_NAME = prop.getProperty("primary_index_name");
        USERS_TYPE_NAME = prop.getProperty("users_type_name");
        RESOURCES_TYPE_NAME = prop.getProperty("resources_type_name");
        EVENTS_TYPE_NAME = prop.getProperty("events_type_name");
        PLACES_TYPE_NAME = prop.getProperty("places_type_name");
        ADDRESS = prop.getProperty("address");
        PORT = prop.getProperty("port");
        SERVICE_NAME = prop.getProperty("service_name");
    }
}
