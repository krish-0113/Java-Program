package com.serverformvalidation.serverFormv.Validation.controller;

import com.serverformvalidation.serverFormv.Validation.entity.UserData;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // ✅ Open Form Page
    @GetMapping("/form")
    public String openForm(Model model) {
        model.addAttribute("userData", new UserData()); // ✅ Fixed: Use 'userData' (same as in HTML)
        return "form"; // ✅ This will render 'form.html' from 'src/main/resources/templates'
    }

    // ✅ Handle Form Submission
    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute("userData") UserData userData,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            System.out.println("Validation Errors: " + result);
            return "form"; // ✅ Show form again if validation fails
        }

        System.out.println("Received Data: " + userData);

        // ✅ Pass data to success page
        model.addAttribute("userData", userData);

        return "success"; // ✅ Redirects to success.html
    }
}
