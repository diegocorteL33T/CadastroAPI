package dev.java10x.usermanagementapi.Users.Service;

import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import dev.java10x.usermanagementapi.Users.Mapper.UserMapper;
import dev.java10x.usermanagementapi.Users.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    //Dependency Injection
    private UserRepository repository;
    private UserMapper mapper;

    //Constructor
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public List<UserEntity> showAllUsers(){
        return repository.findAll();
    }

    public UserEntity showUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.orElse(null);
    }

    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = mapper.map(user);
        UserEntity savedUser = repository.save(userEntity);
        return mapper.map(savedUser);
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
