package dev.java10x.usermanagementapi.Users.ControllerUi;

import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "ShowUsers";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUserByID(@PathVariable Long id){
        service.deleteUserById(id);
        return "redirect:/user/ui/list";
    }


    @GetMapping("/{id}")
    public String showUserByID(@PathVariable Long id, Model model) {
        UserDTO user = service.showUserById(id);

        if(user != null){
            model.addAttribute("user", user);
            return "UserDetails";
        } else {
            model.addAttribute("message","User not found");
            return "redirect:/user/ui/list";
        }
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        UserDTO user = service.showUserById(id);

        if(user != null){
            model.addAttribute("user", user);
            return "UpdateUser";
        } else {
            return "redirect:/user/ui/list";
        }
    }


    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserDTO userDTO) {
        service.updateUser(id, userDTO);
        return "redirect:/user/ui/list";
    }
}