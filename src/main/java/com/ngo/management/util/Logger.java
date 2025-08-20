package com.ngo.management.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

/**
 * Utility class for logging operations throughout the NGO Management System
 */
public class Logger {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("NGOManagementSystem");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Private constructor to prevent instantiation
    private Logger() {}
    
    /**
     * Log an informational message
     */
    public static void info(String message) {
        String formattedMessage = formatMessage("INFO", message);
        logger.info(formattedMessage);
        System.out.println(formattedMessage);
    }
    
    /**
     * Log a warning message
     */
    public static void warning(String message) {
        String formattedMessage = formatMessage("WARNING", message);
        logger.warning(formattedMessage);
        System.out.println(formattedMessage);
    }
    
    /**
     * Log an error message
     */
    public static void error(String message) {
        String formattedMessage = formatMessage("ERROR", message);
        logger.log(Level.SEVERE, formattedMessage);
        System.err.println(formattedMessage);
    }
    
    /**
     * Log an error message with exception details
     */
    public static void error(String message, Throwable throwable) {
        String formattedMessage = formatMessage("ERROR", message);
        logger.log(Level.SEVERE, formattedMessage, throwable);
        System.err.println(formattedMessage);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
    
    /**
     * Log a debug message (only in development mode)
     */
    public static void debug(String message) {
        if (isDebugMode()) {
            String formattedMessage = formatMessage("DEBUG", message);
            logger.fine(formattedMessage);
            System.out.println(formattedMessage);
        }
    }
    
    /**
     * Format the log message with timestamp and level
     */
    private static String formatMessage(String level, String message) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(formatter);
        return String.format("[%s] [%s] %s", timestamp, level, message);
    }
    
    /**
     * Check if debug mode is enabled
     */
    private static boolean isDebugMode() {
        String debugProperty = System.getProperty("ngo.debug");
        return "true".equalsIgnoreCase(debugProperty);
    }
    
    /**
     * Set the logging level
     */
    public static void setLevel(Level level) {
        logger.setLevel(level);
    }
    
    /**
     * Get the underlying Java logger
     */
    public static java.util.logging.Logger getLogger() {
        return logger;
    }
} 