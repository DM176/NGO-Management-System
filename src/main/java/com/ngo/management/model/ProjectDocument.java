package com.ngo.management.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Simplified ProjectDocument model for managing project documents
 */
public class ProjectDocument {
    
    private String documentId;
    private String fileName;
    private String fileType;
    private String description;
    private String filePath;
    private long fileSize;
    private LocalDateTime uploadedAt;
    private String uploadedBy;
    
    public ProjectDocument(String fileName, String fileType, String description, String filePath, long fileSize) {
        this.documentId = "DOC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fileName = fileName;
        this.fileType = fileType;
        this.description = description;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.uploadedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getDocumentId() { return documentId; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    
    public long getFileSize() { return fileSize; }
    public void setFileSize(long fileSize) { this.fileSize = fileSize; }
    
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
    
    public String getFileSizeFormatted() {
        if (fileSize < 1024) return fileSize + " B";
        if (fileSize < 1024 * 1024) return String.format("%.1f KB", fileSize / 1024.0);
        if (fileSize < 1024 * 1024 * 1024) return String.format("%.1f MB", fileSize / (1024.0 * 1024.0));
        return String.format("%.1f GB", fileSize / (1024.0 * 1024.0 * 1024.0));
    }
    
    @Override
    public String toString() {
        return String.format("ProjectDocument[ID=%s, Name=%s, Type=%s, Size=%s]", 
            documentId, fileName, fileType, getFileSizeFormatted());
    }
} 