package com.sparta.scheduleJPA.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import com.sparta.scheduleJPA.dto.SignUpRequestDto;
import com.sparta.scheduleJPA.dto.SignUpResponseDto;
import com.sparta.scheduleJPA.dto.UserResponseDto;
import com.sparta.scheduleJPA.entity.User;
import com.sparta.scheduleJPA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String email, String password){
        User user = new User(name, email, password);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword());
    }

    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getName(), findUser.getEmail());

    }

//    public UserResponseDto updateUserInfo(Long id, String name, String email, String oldPassword, String newPassword){
//        Optional<User> optionalUser = userRepository.findById(id);
//
//        if(optionalUser.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
//        }
//        User findUser = optionalUser.get();
//
//        if(!findUser.getPassword().equals(oldPassword)){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
//        }
//
//
//    }
}
