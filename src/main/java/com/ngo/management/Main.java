package com.ngo.management;

import com.ngo.management.ui.MainDashboard;
import com.ngo.management.util.DatabaseManager;
import com.ngo.management.util.Logger;
import com.ngo.management.util.ConfigurationManager;

import javax.swing.*;

/**
 * Main entry point for the NGO Management System
 * Professional-grade application with modern UI and comprehensive features
 */
public class Main {
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            Logger.error("Failed to set system look and feel", e);
        }
        
        // Initialize configuration
        ConfigurationManager.initialize();
        
        // Initialize database connection
        if (!DatabaseManager.initialize()) {
            Logger.warning("Database unavailable; starting in offline mode");
            // Continue without database - app will work with in-memory data
        }
        
        // Start the application on EDT
        SwingUtilities.invokeLater(() -> {
            try {
                MainDashboard dashboard = new MainDashboard();
                dashboard.setVisible(true);
                Logger.info("NGO Management System started successfully");
            } catch (Exception e) {
                Logger.error("Failed to start application", e);
                JOptionPane.showMessageDialog(null, 
                    "Failed to start application: " + e.getMessage(),
                    "Startup Error", 
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
} 