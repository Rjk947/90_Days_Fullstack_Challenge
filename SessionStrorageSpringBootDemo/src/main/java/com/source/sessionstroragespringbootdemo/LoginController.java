package com.source.sessionstroragespringbootdemo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        if ("admin".equals(username) && "password".equals(password)) {
            session.setAttribute("username", username);
            return "Logged in. Session ID: " + session.getId();
        } else {
            return "Invalid credentials";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        Object username = session.getAttribute("username");
        if (username != null) {
            return "Welcome " + username;
        } else {
            return "Please login first";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "Logged out successfully";
    }

}
