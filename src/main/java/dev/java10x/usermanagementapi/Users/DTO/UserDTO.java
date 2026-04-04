package dev.java10x.usermanagementapi.Users.DTO;

import dev.java10x.usermanagementapi.Tasks.Entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private int age;
    private String rank;
    private TaskEntity task;

}
