package caoh29.OMS.auth_server.controllers;

import caoh29.OMS.auth_server.entities.Client;
import caoh29.OMS.auth_server.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/auth/users")
    public List<Client> getUsers() {
        return this.userService.findAll();
    }

//    @GetMapping("/auth/user")
//    public User getUserByEmail(
//            @RequestParam(name = "email")
//            String email
//    ) {
//        return this.userService.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
//    }

//    @GetMapping("/auth/login")
//    public String login() {
//        return "login"; // This should return the name of the login view
//    }

}
