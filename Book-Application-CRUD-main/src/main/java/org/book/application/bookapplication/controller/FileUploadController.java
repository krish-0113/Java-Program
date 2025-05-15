package org.book.application.bookapplication.controller;

import org.book.application.bookapplication.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        // 1. Check if file is empty
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request must contain a file");
        }

        // 2. Validate file type
        if (!file.getContentType().equals("image/png") && !file.getContentType().equals("image/jpeg")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Only PNG and JPEG files are allowed");
        }

        // 3. Upload file using helper class
        boolean uploaded = fileUploadHelper.uploadFile(file);

        if (uploaded) {
//            return ResponseEntity.ok("File uploaded successfully");
            //return url ServletUriComponentsBuilder.fromCurrentContextPath().path("/img/") = locatlhost:9090/img/imgae name milega
            // Return the URL of the uploaded file
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/img/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            return ResponseEntity.ok(fileDownloadUri);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, try again");
        }
    }
}
