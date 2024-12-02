package com.prj666.group1.petadoptionsystem.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    private Path fileStorageLocation;

    @Value("${image.upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        this.fileStorageLocation = Paths.get(uploadDir)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * Store the uploaded image and return the filename.
     *
     * @param file Multipart file to store
     * @return Filename of the stored file
     */
    public String storeImage(MultipartFile file) {
        // Normalize file name
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExtension = getFileExtension(originalFilename);

        // Generate a unique filename to prevent collisions
        String filename = UUID.randomUUID()+ "." + fileExtension;

        try {
            // Check if the file's name contains invalid characters
            if(originalFilename.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + originalFilename);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + filename + ". Please try again!", ex);
        }
    }

    /**
     * Get the file extension from filename.
     *
     * @param filename Name of the file
     * @return File extension
     */
    private String getFileExtension(String filename) {
        if(filename == null) {
            return "";
        }
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }
}