package com.ra.controller;

import com.ra.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class userController {
    @GetMapping("/register")
    public String register(Model model){
        // khởi tạo đối user
        User user = new User();
        // mang dũ liệu sang view
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/post-register")
    public String postRegister(@ModelAttribute("user") User user){
        System.out.println(user.getFullName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return "redirect:/";
    }
}