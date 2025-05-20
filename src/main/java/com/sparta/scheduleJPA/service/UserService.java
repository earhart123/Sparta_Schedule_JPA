package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.SignUpRequestDto;
import com.sparta.scheduleJPA.dto.SignUpResponseDto;
import com.sparta.scheduleJPA.entity.User;
import com.sparta.scheduleJPA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String email, String password){
        User user = new User(name, email, password);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword());
    }
}
