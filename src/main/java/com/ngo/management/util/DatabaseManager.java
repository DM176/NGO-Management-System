package com.ngo.management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections
 */
public class DatabaseManager {
    
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/ngo_management";
    private static final String DEFAULT_USERNAME = "root";
    private static final String DEFAULT_PASSWORD = "password";
    
    // H2 embedded fallback (file-based DB under ./data)
    private static final String H2_URL = "jdbc:h2:./data/ngo_db;MODE=MySQL;AUTO_SERVER=TRUE";
    private static final String H2_USERNAME = "sa";
    private static final String H2_PASSWORD = "";
    
    private static Connection connection;
    private static boolean isInitialized = false;
    
    // Private constructor to prevent instantiation
    private DatabaseManager() {}
    
    /**
     * Initialize the database connection
     */
    public static boolean initialize() {
        // 1) Try MySQL first (if driver present)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = System.getProperty("ngo.db.url", DEFAULT_URL);
            String username = System.getProperty("ngo.db.username", DEFAULT_USERNAME);
            String password = System.getProperty("ngo.db.password", DEFAULT_PASSWORD);
            connection = DriverManager.getConnection(url, username, password);
            isInitialized = true;
            Logger.info("Database connection established successfully (MySQL)");
            return true;
        } catch (ClassNotFoundException e) {
            Logger.warning("MySQL driver not found. Falling back to embedded H2 database.");
        } catch (SQLException e) {
            Logger.warning("MySQL connection failed. Falling back to embedded H2 database.");
        }

        // 2) Fallback to H2 embedded (no external setup required)
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(H2_URL, H2_USERNAME, H2_PASSWORD);
            isInitialized = true;
            Logger.info("Database connection established successfully (H2 embedded)");
            return true;
        } catch (ClassNotFoundException e) {
            Logger.error("H2 JDBC driver not found", e);
            return false;
        } catch (SQLException e) {
            Logger.error("Failed to establish H2 database connection", e);
            return false;
        }
    }
    
    /**
     * Get the current database connection
     */
    public static Connection getConnection() throws SQLException {
        if (!isInitialized || connection == null || connection.isClosed()) {
            if (!initialize()) {
                throw new SQLException("Failed to initialize database connection");
            }
        }
        return connection;
    }
    
    /**
     * Close the database connection
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                Logger.info("Database connection closed");
            }
        } catch (SQLException e) {
            Logger.error("Error closing database connection", e);
        }
    }
    
    /**
     * Check if the database is connected
     */
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
    
    /**
     * Test the database connection
     */
    public static boolean testConnection() {
        try {
            Connection testConn = getConnection();
            boolean isValid = testConn.isValid(5); // 5 second timeout
            Logger.info("Database connection test: " + (isValid ? "SUCCESS" : "FAILED"));
            return isValid;
        } catch (SQLException e) {
            Logger.error("Database connection test failed", e);
            return false;
        }
    }
    
    /**
     * Get database connection information
     */
    public static String getConnectionInfo() {
        if (connection == null) {
            return "No connection established";
        }
        
        try {
            return String.format("Database: %s, Connected: %s", 
                connection.getMetaData().getDatabaseProductName(),
                !connection.isClosed());
        } catch (SQLException e) {
            return "Connection info unavailable";
        }
    }
} 