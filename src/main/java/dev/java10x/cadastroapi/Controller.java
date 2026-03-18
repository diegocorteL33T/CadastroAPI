package dev.java10x.cadastroapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping("/welcome")
    public String welcome(){
        return  "this is the first message of this route";
    }



}
