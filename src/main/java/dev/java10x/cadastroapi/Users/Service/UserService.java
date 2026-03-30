package dev.java10x.cadastroapi.Users.Service;

import dev.java10x.cadastroapi.Users.Entity.UserEntity;
import dev.java10x.cadastroapi.Users.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserEntity showUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.orElse(null);
    }





}
