package dev.java10x.usermanagementapi.Tasks.Repository;

import dev.java10x.usermanagementapi.Tasks.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
