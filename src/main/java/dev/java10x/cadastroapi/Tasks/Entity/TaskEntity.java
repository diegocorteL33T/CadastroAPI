package dev.java10x.cadastroapi.Tasks.Entity;

import dev.java10x.cadastroapi.Tasks.Enums.TaskDifficulty;
import dev.java10x.cadastroapi.Users.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private List<UserEntity> users;
}


