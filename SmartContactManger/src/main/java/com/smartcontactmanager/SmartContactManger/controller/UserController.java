package com.smartcontactmanager.SmartContactManger.controller;

import com.smartcontactmanager.SmartContactManger.entity.Contact;
import com.smartcontactmanager.SmartContactManger.entity.User;
import com.smartcontactmanager.SmartContactManger.repository.ContactRepository;
import com.smartcontactmanager.SmartContactManger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    // ✅ Change to GET for fetching user details
    @GetMapping("/dashboard")
    public ResponseEntity<User> dashboard(Principal principal) {
        String userName = principal.getName(); // logged-in user
        User user = userRepository.findByEmail(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }


    //add contact handler
    @GetMapping("/add-contact")
    public ResponseEntity<String> openFormHandler() {
        return ResponseEntity.ok("This is add contact form");
    }

    // Show contact handler - returns all contacts of logged-in user
    //pagination per page = 10[n]
    //curr page -= 0 [page]
    @GetMapping("/show-contact/{page}")
    public ResponseEntity<?> showContacts(@PathVariable("page") Integer page,Principal principal) {
        try {
            // 1. Get email of logged-in user
            String loggedUserName = principal.getName();

            // 2. Get User from DB
            Optional<User> optionalUser = userRepository.findByEmail(loggedUserName);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                // 3. Get contacts
                List<Contact> contacts = user.getContacts();

                if (contacts == null || contacts.isEmpty()) {
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Contact list is empty, you need to add contacts.");
                }

                // 4. Return contacts
                return ResponseEntity.ok(contacts);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("User not found!");
            }

        } catch (Exception e) {
            // 5. Handle unexpected error
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }


    //submit add contact form
    @PostMapping("/process-contact")
    public ResponseEntity<String> processContact(
            @ModelAttribute Contact contact,
            @RequestParam(value = "ProfileImage", required = false) MultipartFile file,
            Principal principal) {

        try {
            // ✅ Get current user
            String email = principal.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            contact.setUser(user);  // Set user once

            String message;

            // ✅ File Upload Handling
            if (file == null || file.isEmpty()) {
                message = "No file uploaded. Using default image.";
                contact.setImage("default.png");
            } else {
                // ✅ Ensure upload directory exists
                String uploadDirPath = System.getProperty("user.dir") + "/uploads";
                File uploadDir = new File(uploadDirPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // ✅ Save file
                Path path = Paths.get(uploadDirPath, file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                contact.setImage(file.getOriginalFilename());
                message = "Image uploaded successfully: " + file.getOriginalFilename();
            }

            // ✅ Save contact
            contactRepository.save(contact);

            return ResponseEntity.ok("Successfully added contact! " + message);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
}
