package org.book.application.bookapplication.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Component
public class FileUploadHelper {

    // Define the upload directory dynamically in the project root
    private final Path UPLOAD_DIR;

    public FileUploadHelper() throws IOException {
        // Define the directory where files will be uploaded
       //private final Path UPLOAD_DIR = Paths.get("E:\\Spring-Boot-Program\\Book-Application-CRUD-main\\src\\main\\resources\\static\\img");
        // Store files in a dedicated "uploads" folder in the project root
        this.UPLOAD_DIR = Paths.get(System.getProperty("user.dir"), "uploads");

        // Ensure the directory exists
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectories(UPLOAD_DIR);
        }
    }

    public boolean uploadFile(MultipartFile multipartFile) {
        try {
            // Define the path where the file should be stored
            Path filePath = UPLOAD_DIR.resolve(multipartFile.getOriginalFilename());

            // Copy file using NIO (REPLACE_EXISTING allows overwriting)
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return true; // File uploaded successfully
        } catch (IOException e) {
            e.printStackTrace();
            return false; // File upload failed
        }
    }

    public String getUploadDirPath() {
        return UPLOAD_DIR.toString();
    }
}




/*
    // Alternative Approach: Using FileOutputStream (Not Recommended for Large Files)
    public boolean uploadFileUsingStream(MultipartFile multipartFile) {
        boolean success = false;
        try {
            // Read the file as an is
            InputStream is = multipartFile.getInputStream();

            // Create a byte array to store file data
            byte[] data = new byte[is.available()];
            is.read(data); // Read data from the file into byte array

            // Write the file to the specified directory
            FileOutputStream fos = new FileOutputStream(UPLOAD_DIR.toString() + File.separator + multipartFile.getOriginalFilename());
            fos.write(data);
            fos.flush();
            fos.close();

            success = true; // File uploaded successfully
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success; // Return whether upload was successful or not
    }
    */

