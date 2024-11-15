package com.quangbui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

@RestController
public class ImageProxyController {

    private static final Logger logger = LoggerFactory.getLogger(ImageProxyController.class);

    @GetMapping("/proxy-image")
    public ResponseEntity<Resource> proxyImage(@RequestParam("id") String fileId) {
        logger.info("Received request to proxy image with ID: {}", fileId);
        
        try {
            String imageUrl = "https://drive.usercontent.google.com/download?id=" + fileId + "&authuser=0";
            logger.debug("Constructed image URL: {}", imageUrl);           
            Resource resource = new UrlResource(imageUrl);
            if (resource.exists() || resource.isReadable()) {
                logger.info("Successfully retrieved resource for image ID: {}", imageUrl);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                logger.error("Resource does not exist or is not readable for image ID: {}", fileId);
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            logger.error("Error creating URL for image ID: {}", fileId, e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Unexpected error when proxying image with ID: {}", fileId, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}