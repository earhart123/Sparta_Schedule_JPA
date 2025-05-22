package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.LoginResponseDto;
import com.sparta.scheduleJPA.dto.SignUpResponseDto;
import com.sparta.scheduleJPA.dto.UserResponseDto;
import com.sparta.scheduleJPA.entity.User;
import com.sparta.scheduleJPA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String email, String password){
        User user = new User(name, email, password);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPassword());
    }

    public LoginResponseDto login(String email, String password){
        Long findIndex = findIdByEmailAndPassword(email, password);
        if(findIndex == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유효한 회원 정보가 존재하지 않습니다.");
        }
        return new LoginResponseDto(findIndex);

    }

    public Long findIdByEmailAndPassword(String email, String password){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .map(User::getId)
                .findFirst()
                .orElse(null);
    }

    public List<UserResponseDto> findByName(String name){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .filter(user -> user.getEmail().equals(name))
                .map(user -> new UserResponseDto(user.getName(), user.getEmail()))
                .collect(Collectors.toList());
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
