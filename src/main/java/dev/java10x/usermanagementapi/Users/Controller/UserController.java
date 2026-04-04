package dev.java10x.usermanagementapi.Users.Controller;

import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import dev.java10x.usermanagementapi.Users.Service.UserService;
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
    public UserDTO createUser(@RequestBody UserDTO user){

        /*
        *   @RequestBody in Spring Boot tells the controller to read
        *   the HTTP request body and convert it into a Java object.
        *   In this method, the JSON sent by the client is deserialized
        *   into UserEntity user automatically, used in POST AND PUT endpoints
        *
        *  */

        return service.createUser(user);

    }

    @GetMapping("/list")
    public List<UserEntity> showAllUsers() { return service.showAllUsers(); }

    @GetMapping("/list/{id}")
    public UserEntity showUserByID(@PathVariable Long id){ return service.showUserById(id); }

    @PutMapping("/update/{id}")
    public UserEntity updateUser(@RequestParam Long id, @RequestBody UserEntity existingUser){
        return service.updateUser(id,existingUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserByID(@PathVariable Long id){ service.deleteUserById(id); }



}
