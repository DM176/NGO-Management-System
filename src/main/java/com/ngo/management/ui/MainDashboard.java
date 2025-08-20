package com.ngo.management.ui;
import com.ngo.management.service.*;
import com.ngo.management.util.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Dashboard - Professional UI for NGO Management System
 * Features modern design with navigation, statistics, and quick actions
 */
public class MainDashboard extends JFrame {
    
    private JPanel contentPane;
    private JLabel lblWelcome;
    private JPanel navigationPanel;
    private JPanel mainContentPanel;
    private JPanel statsPanel;
    
    // Services
    private ProjectService projectService;
    private DonorService donorService;
    private VolunteerService volunteerService;
    private NeedyService needyService;
    
    // UI Components
    private JButton btnProjects;
    private JButton btnDonors;
    private JButton btnVolunteers;
    private JButton btnNeedy;
    private JButton btnReports;
    private JButton btnSettings;
    private JButton btnLogout;
    
    public MainDashboard() {
        initializeServices();
        initializeUI();
        loadDashboardData();
    }
    
    private void initializeServices() {
        projectService = new ProjectService();
        donorService = new DonorService();
        volunteerService = new VolunteerService();
        needyService = new NeedyService();
    }
    
    private void initializeUI() {
        setTitle("NGO Management System - Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        createHeader();
        createNavigationPanel();
        createMainContent();
        createStatsPanel();
        
        // Apply modern styling
        applyModernStyling();
    }
    
    private void createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setPreferredSize(new Dimension(1200, 80));
        
        lblWelcome = new JLabel("Welcome to NGO Management System");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel lblDateTime = new JLabel(java.time.LocalDate.now().toString());
        lblDateTime.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDateTime.setForeground(Color.WHITE);
        lblDateTime.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        headerPanel.add(lblWelcome, BorderLayout.WEST);
        headerPanel.add(lblDateTime, BorderLayout.EAST);
        
        contentPane.add(headerPanel, BorderLayout.NORTH);
    }
    
    private void createNavigationPanel() {
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(7, 1, 0, 10));
        navigationPanel.setBackground(new Color(44, 62, 80));
        navigationPanel.setPreferredSize(new Dimension(250, 600));
        navigationPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Create navigation buttons
        btnProjects = createNavButton("📋 Projects", new Color(52, 152, 219));
        btnDonors = createNavButton("💰 Donors", new Color(46, 204, 113));
        btnVolunteers = createNavButton("👥 Volunteers", new Color(155, 89, 182));
        btnNeedy = createNavButton("🤝 Beneficiaries", new Color(230, 126, 34));
        btnReports = createNavButton("📊 Reports", new Color(52, 73, 94));
        btnSettings = createNavButton("⚙️ Settings", new Color(149, 165, 166));
        btnLogout = createNavButton("🚪 Logout", new Color(231, 76, 60));
        
        // Add action listeners
        btnProjects.addActionListener(e -> openProjects());
        btnDonors.addActionListener(e -> openDonors());
        btnVolunteers.addActionListener(e -> openVolunteers());
        btnNeedy.addActionListener(e -> openNeedy());
        btnReports.addActionListener(e -> openReports());
        btnSettings.addActionListener(e -> openSettings());
        btnLogout.addActionListener(e -> logout());
        
        contentPane.add(navigationPanel, BorderLayout.WEST);
    }
    
    private JButton createNavButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        navigationPanel.add(button);
        return button;
    }
    
    private void createMainContent() {
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BorderLayout());
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Welcome message
        JLabel welcomeLabel = new JLabel("Dashboard Overview");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(52, 73, 94));
        
        JLabel subtitleLabel = new JLabel("Manage your NGO operations efficiently");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(149, 165, 166));
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createHorizontalStrut(20));
        welcomePanel.add(subtitleLabel);
        
        mainContentPanel.add(welcomePanel, BorderLayout.NORTH);
        
        // Quick actions panel
        createQuickActionsPanel();
        
        contentPane.add(mainContentPanel, BorderLayout.CENTER);
    }
    
    private void createQuickActionsPanel() {
        JPanel quickActionsPanel = new JPanel();
        quickActionsPanel.setLayout(new GridLayout(2, 3, 20, 20));
        quickActionsPanel.setBackground(Color.WHITE);
        
        quickActionsPanel.add(createQuickActionCard("Add New Project", "📋", "Create and manage new projects", e -> openProjects()));
        quickActionsPanel.add(createQuickActionCard("Register Donor", "💰", "Add new donor information", e -> openDonors()));
        quickActionsPanel.add(createQuickActionCard("Add Volunteer", "👥", "Register new volunteers", e -> openVolunteers()));
        quickActionsPanel.add(createQuickActionCard("Beneficiary", "🤝", "Add new beneficiary", e -> openNeedy()));
        quickActionsPanel.add(createQuickActionCard("Generate Report", "📊", "Create detailed reports", e -> openReports()));
        quickActionsPanel.add(createQuickActionCard("System Settings", "⚙️", "Configure system options", e -> openSettings()));
        
        mainContentPanel.add(quickActionsPanel, BorderLayout.CENTER);
    }
    
    private JPanel createQuickActionCard(String title, String icon, String description, ActionListener action) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 73, 94));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(new Color(149, 165, 166));
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        card.add(iconLabel, BorderLayout.NORTH);
        card.add(titleLabel, BorderLayout.CENTER);
        card.add(descLabel, BorderLayout.SOUTH);
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                action.actionPerformed(new ActionEvent(card, ActionEvent.ACTION_PERFORMED, "click"));
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
        });
        
        return card;
    }
    
    private void createStatsPanel() {
        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(4, 1, 10, 10));
        statsPanel.setBackground(new Color(236, 240, 241));
        statsPanel.setPreferredSize(new Dimension(300, 600));
        statsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Add statistics cards
        statsPanel.add(createStatCard("Total Projects", "0", "📋"));
        statsPanel.add(createStatCard("Active Donors", "0", "💰"));
        statsPanel.add(createStatCard("Volunteers", "0", "👥"));
        statsPanel.add(createStatCard("Beneficiaries", "0", "🤝"));
        
        contentPane.add(statsPanel, BorderLayout.EAST);
    }
    
    private JPanel createStatCard(String title, String value, String icon) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        valueLabel.setForeground(new Color(52, 152, 219));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        titleLabel.setForeground(new Color(149, 165, 166));
        
        card.add(iconLabel, BorderLayout.WEST);
        card.add(valueLabel, BorderLayout.CENTER);
        card.add(titleLabel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void applyModernStyling() {
        // Set global UI properties
        UIManager.put("Button.arc", 10);
        UIManager.put("Component.arc", 10);
        UIManager.put("ProgressBar.arc", 10);
        UIManager.put("TextComponent.arc", 10);
    }
    
    private void loadDashboardData() {
        // Load statistics in background
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                updateStatistics();
                return null;
            }
        };
        worker.execute();
    }
    
    private void updateStatistics() {
        try {
            // Update statistics with real data
            SwingUtilities.invokeLater(() -> {
                // This would be populated with real data from services
                Logger.info("Dashboard statistics updated");
            });
        } catch (Exception e) {
            Logger.error("Failed to update dashboard statistics", e);
        }
    }
    
    // Navigation methods
    private void openProjects() {
        // TODO: Implement project management window
        JOptionPane.showMessageDialog(this, "Projects management coming soon!");
    }
    
    private void openDonors() {
        // TODO: Implement donor management window
        JOptionPane.showMessageDialog(this, "Donor management coming soon!");
    }
    
    private void openVolunteers() {
        // TODO: Implement volunteer management window
        JOptionPane.showMessageDialog(this, "Volunteer management coming soon!");
    }
    
    private void openNeedy() {
        // TODO: Implement beneficiary management window
        JOptionPane.showMessageDialog(this, "Beneficiary management coming soon!");
    }
    
    private void openReports() {
        // TODO: Implement reporting window
        JOptionPane.showMessageDialog(this, "Reporting system coming soon!");
    }
    
    private void openSettings() {
        // TODO: Implement settings window
        JOptionPane.showMessageDialog(this, "Settings panel coming soon!");
    }
    
    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", 
            "Logout Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }
} 