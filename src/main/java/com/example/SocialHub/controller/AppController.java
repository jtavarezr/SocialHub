package com.example.SocialHub.controller;

import com.example.SocialHub.entity.User;
import com.example.SocialHub.repository.UserRepository;
import com.example.SocialHub.services.UserServices;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserServices service;

  @GetMapping("/")
  public String viewHomePage(){
    return "index";
  }
  @GetMapping("/register")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());

    return "signup_form";
  }

  /*@PostMapping("/process_register")
  public String processRegister(User user) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);

    userRepository.save(user);

    return "register_success";
  }*/
  @PostMapping("/process_register")
  public String processRegister(User user, HttpServletRequest request)
      throws UnsupportedEncodingException, MessagingException {
    service.register(user, getSiteURL(request));
    return "register_success";
  }

  private String getSiteURL(HttpServletRequest request) {
    String siteURL = request.getRequestURL().toString();
    return siteURL.replace(request.getServletPath(), "");
  }

  @GetMapping("/users")
  public String listUsers(Model model) {
    List<User> listUsers = userRepository.findAll();
    model.addAttribute("listUsers", listUsers);

    return "users";
  }

  @GetMapping("/verify")
  public String verifyUser(@Param("code") String code) {
    if (service.verify(code)) {
      return "verify_success";
    } else {
      return "verify_fail";
    }
  }
}
