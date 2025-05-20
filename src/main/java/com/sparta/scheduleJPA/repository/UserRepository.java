package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
