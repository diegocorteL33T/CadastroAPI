package dev.java10x.usermanagementapi.Users.Mapper;


import dev.java10x.usermanagementapi.Users.DTO.UserDTO;
import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity map(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setAge(userDTO.getAge());
        userEntity.setRank(userDTO.getRank());
        userEntity.setTask(userDTO.getTask());
        userEntity.setProfile_picture(userDTO.getProfile_picture());

        return userEntity;
    }

    public UserDTO map(UserEntity userEntity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setAge(userEntity.getAge());
        userDTO.setRank(userEntity.getRank());
        userDTO.setTask(userEntity.getTask());
        userDTO.setProfile_picture(userEntity.getProfile_picture());

        return userDTO;
    }

}
