package dev.java10x.usermanagementapi.Users.Controller;

import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new user", description = "Registers a new user in the system with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "User creation error")
    })
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
                .body("User created successfully: " + newUser.getName());

    }

    @GetMapping("/list")
    @Operation(summary = "Get all users", description = "Retrieves a list of all registered users in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No users found")
    })
    public ResponseEntity<List<UserDTO>> showAllUsers() {
        List<UserDTO> users = service.showAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/list/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves the details of a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
    @Operation(summary = "Update user by ID", description = "Updates the details of an existing user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
        public ResponseEntity<?> updateUser(
                @Parameter(description = "ID of the user to be updated", required = true)
                @PathVariable Long id,
                @Parameter(description = "Updated user details", required = true)
                @RequestBody UserDTO existingUser){

        UserDTO user = service.updateUser(id, existingUser);
        if(user != null){
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with id " + id);
        }

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by ID", description = "Deletes an existing user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
