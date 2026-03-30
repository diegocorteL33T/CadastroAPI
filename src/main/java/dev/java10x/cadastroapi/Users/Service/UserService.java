package dev.java10x.cadastroapi.Users.Service;

import dev.java10x.cadastroapi.Users.Entity.UserEntity;
import dev.java10x.cadastroapi.Users.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    //Dependency Injection
    private UserRepository repository;

    //Constructor
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserEntity> showAllUsers(){
        return repository.findAll();
    }





}
