package dev.java10x.cadastroapi.Users.Controller;

import dev.java10x.cadastroapi.Users.Entity.UserEntity;
import dev.java10x.cadastroapi.Users.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //Dependency Injection
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return  "this is the first message of this route";
    }

    //Add user (Create)

    @PostMapping("/register")
    public String createUser(){
        return "User successfully created";
    }

    //Show all Users (Read)

    @GetMapping("/all")
    public List<UserEntity> showAllUsers() {
    return service.showAllUsers();
    }

    //Sort User by ID (Read)

    @GetMapping("/showuserbyid")
    public String showUserByID(){
        return "Show user by ID";
    }

    //Update User data (Update)

    @PutMapping("/update")
    public String updateUserByID(){
        return "Update user by ID";
    }

    //Delete User (Delete)

    @DeleteMapping("/deletebyid")
    public String deleteUserByID(){
        return  "User deleted by id";
    }




}
