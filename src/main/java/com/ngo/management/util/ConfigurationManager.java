package com.ngo.management.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for managing system configuration
 */
public class ConfigurationManager {
    
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();
    private static boolean isInitialized = false;
    
    // Private constructor to prevent instantiation
    private ConfigurationManager() {}
    
    /**
     * Initialize the configuration manager
     */
    public static void initialize() {
        if (isInitialized) {
            return;
        }
        
        try {
            // Load default properties
            loadDefaultProperties();
            
            // Try to load from file
            loadFromFile();
            
            isInitialized = true;
            Logger.info("Configuration manager initialized successfully");
            
        } catch (Exception e) {
            Logger.error("Failed to initialize configuration manager", e);
            // Continue with default properties
        }
    }
    
    /**
     * Load default configuration properties
     */
    private static void loadDefaultProperties() {
        // Database configuration
        properties.setProperty("ngo.db.url", "jdbc:mysql://localhost:3306/ngo_management");
        properties.setProperty("ngo.db.username", "root");
        properties.setProperty("ngo.db.password", "password");
        properties.setProperty("ngo.db.driver", "com.mysql.cj.jdbc.Driver");
        
        // Application configuration
        properties.setProperty("ngo.app.name", "NGO Management System");
        properties.setProperty("ngo.app.version", "2.0.0");
        properties.setProperty("ngo.app.debug", "false");
        
        // UI configuration
        properties.setProperty("ngo.ui.theme", "system");
        properties.setProperty("ngo.ui.language", "en");
        properties.setProperty("ngo.ui.timezone", "UTC");
        
        // Logging configuration
        properties.setProperty("ngo.log.level", "INFO");
        properties.setProperty("ngo.log.file", "ngo_system.log");
        properties.setProperty("ngo.log.maxSize", "10MB");
        
        // Security configuration
        properties.setProperty("ngo.security.sessionTimeout", "30");
        properties.setProperty("ngo.security.maxLoginAttempts", "3");
        properties.setProperty("ngo.security.passwordPolicy", "strong");
        
        Logger.info("Default configuration properties loaded");
    }
    
    /**
     * Load configuration from file
     */
    private static void loadFromFile() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            Logger.info("Configuration loaded from file: " + CONFIG_FILE);
        } catch (IOException e) {
            Logger.warning("Configuration file not found, using defaults: " + CONFIG_FILE);
        }
    }
    
    /**
     * Save configuration to file
     */
    public static void saveConfiguration() {
        try (FileOutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "NGO Management System Configuration");
            Logger.info("Configuration saved to file: " + CONFIG_FILE);
        } catch (IOException e) {
            Logger.error("Failed to save configuration to file", e);
        }
    }
    
    /**
     * Get a configuration property
     */
    public static String getProperty(String key) {
        if (!isInitialized) {
            initialize();
        }
        return properties.getProperty(key);
    }
    
    /**
     * Get a configuration property with default value
     */
    public static String getProperty(String key, String defaultValue) {
        if (!isInitialized) {
            initialize();
        }
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Set a configuration property
     */
    public static void setProperty(String key, String value) {
        if (!isInitialized) {
            initialize();
        }
        properties.setProperty(key, value);
        Logger.info("Configuration property set: " + key + " = " + value);
    }
    
    /**
     * Get an integer configuration property
     */
    public static int getIntProperty(String key, int defaultValue) {
        try {
            String value = getProperty(key);
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            Logger.warning("Invalid integer value for property: " + key + ", using default: " + defaultValue);
            return defaultValue;
        }
    }
    
    /**
     * Get a boolean configuration property
     */
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
    
    /**
     * Get a double configuration property
     */
    public static double getDoubleProperty(String key, double defaultValue) {
        try {
            String value = getProperty(key);
            return value != null ? Double.parseDouble(value) : defaultValue;
        } catch (NumberFormatException e) {
            Logger.warning("Invalid double value for property: " + key + ", using default: " + defaultValue);
            return defaultValue;
        }
    }
    
    /**
     * Check if a property exists
     */
    public static boolean hasProperty(String key) {
        if (!isInitialized) {
            initialize();
        }
        return properties.containsKey(key);
    }
    
    /**
     * Remove a configuration property
     */
    public static void removeProperty(String key) {
        if (!isInitialized) {
            initialize();
        }
        properties.remove(key);
        Logger.info("Configuration property removed: " + key);
    }
    
    /**
     * Get all configuration properties
     */
    public static Properties getAllProperties() {
        if (!isInitialized) {
            initialize();
        }
        return new Properties(properties);
    }
    
    /**
     * Reload configuration from file
     */
    public static void reloadConfiguration() {
        isInitialized = false;
        properties.clear();
        initialize();
        Logger.info("Configuration reloaded");
    }
    
    /**
     * Get configuration summary
     */
    public static String getConfigurationSummary() {
        if (!isInitialized) {
            initialize();
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("Configuration Summary:\n");
        summary.append("====================\n");
        
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            // Mask sensitive information
            if (key.contains("password")) {
                value = "***";
            }
            summary.append(key).append(" = ").append(value).append("\n");
        }
        
        return summary.toString();
    }
} 