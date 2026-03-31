package dev.java10x.cadastroapi.Users.Service;

import dev.java10x.cadastroapi.Users.Entity.UserEntity;
import dev.java10x.cadastroapi.Users.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public UserEntity createUser(UserEntity user) {
        return repository.save(user);
    }

    public UserEntity updateUser(Long id,UserEntity existingUser){
        if(!repository.existsById(id)) { return null; }
        existingUser.setId(id);
        return repository.save(existingUser);
    }

    public void deleteUserById(Long id){
    repository.deleteById(id);
    }

}
