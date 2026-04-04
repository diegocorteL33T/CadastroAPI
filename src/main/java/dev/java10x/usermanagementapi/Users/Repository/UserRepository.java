package dev.java10x.usermanagementapi.Users.Repository;

import dev.java10x.usermanagementapi.Users.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
