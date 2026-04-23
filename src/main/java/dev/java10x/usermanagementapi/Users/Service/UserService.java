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
    private final UserRepository repository;
    private final UserMapper mapper;

    //Constructor
    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public List<UserDTO> showAllUsers(){
        List<UserEntity> users = repository.findAll();
        return users.stream()
                .map(mapper::map)
                .toList();
    }

    public UserDTO showUserById(Long id) {
        Optional<UserEntity> user = repository.findById(id);
        return user.map(mapper::map).orElse(null);
    }

    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = mapper.map(user);
        UserEntity savedUser = repository.save(userEntity);
        return mapper.map(savedUser);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {

        Optional<UserEntity> optionalUser = repository.findById(id);

        if (optionalUser.isEmpty()) return null;

        UserEntity existingUser = optionalUser.get();

        if (userDTO.getName() != null) {
            existingUser.setName(userDTO.getName());
        }

        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }

        if (userDTO.getRank() != null) {
            existingUser.setRank(userDTO.getRank());
        }

        if (userDTO.getTask() != null) {
            existingUser.setTask(userDTO.getTask());
        }

        if (userDTO.getAge() != 0) {
            existingUser.setAge(userDTO.getAge());
        }

        UserEntity savedUser = repository.save(existingUser);

        return mapper.map(savedUser);
    }

    public void deleteUserById(Long id){ repository.deleteById(id); }

}
