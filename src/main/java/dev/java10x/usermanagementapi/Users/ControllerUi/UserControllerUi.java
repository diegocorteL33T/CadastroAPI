package dev.java10x.usermanagementapi.Users.ControllerUi;


import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/ui")
public class UserControllerUi {

    private final UserService service;

    public UserControllerUi(UserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String showAllUsers(Model model) {
        List<UserDTO> users = service.showAllUsers();
        model.addAttribute("users", users);
        return "ShowUsers"; // returns the rendered HTML page name
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserByID(@PathVariable Long id){
        service.deleteUserById(id);
        return "redirect:/user/ui/list"; // Redirect to the list page after deletion
    }

}
