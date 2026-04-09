package dev.java10x.usermanagementapi.Users.Entity;

import dev.java10x.usermanagementapi.Tasks.Entity.TaskEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


//JPA -> Java Persistence API
@Entity // Turns a Class into a DB Entity
@Table(name = "tb_users")
@Data //Getter and Setters
@NoArgsConstructor
@ToString(exclude = "task")

public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int age;

    private String rank;

    // @ManyToOne - a user have only one task
    @ManyToOne
    @JoinColumn(name = "task_id") // Foreign Key
    private TaskEntity task;





}
