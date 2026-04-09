package dev.java10x.usermanagementapi.Users.Controller;

import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import dev.java10x.usermanagementapi.Users.Service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //Dependency Injection
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserDTO user){

        /*
        *   @RequestBody in Spring Boot tells the controller to read
        *   the HTTP request body and convert it into a Java object.
        *   In this method, the JSON sent by the client is deserialized
        *   into UserEntity user automatically, used in POST AND PUT endpoints
        *
        *  */

        UserDTO newUser = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User created successfully: " + newUser.getEmail());

    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> showAllUsers() {
        List<UserDTO> users = service.showAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> showUserByID(@PathVariable Long id){
        UserDTO user = service.showUserById(id);
        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + id);
        }

    }

    @PutMapping("/update/{id}")
        public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO existingUser){
        UserDTO user = service.updateUser(id, existingUser);
        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + id);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserByID(@PathVariable Long id){
        UserDTO user = service.showUserById(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id " + id);
        } else {
            service.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
        }

    }

}
