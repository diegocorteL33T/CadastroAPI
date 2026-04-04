package dev.java10x.usermanagementapi.Tasks.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.java10x.usermanagementapi.Tasks.Enums.TaskDifficulty;
import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_tasks")
@Data
@NoArgsConstructor

public class TaskEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TaskDifficulty difficulty;

    //OneToMany - a task have many users
    @OneToMany(mappedBy = "task")
    @JsonIgnore
    private List<UserEntity> users;
}


