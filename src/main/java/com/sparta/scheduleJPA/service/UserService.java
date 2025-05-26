package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.*;
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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효한 회원 정보가 존재하지 않습니다.");
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
                .filter(user -> user.getName().equals(name))
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

    public UpdateUserResponseDto updateUserInfo(Long id, UpdateUserRequestDto requestDto){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        User findUser = optionalUser.get();

        // 로그인한 유저의 비밀번호와 입력한 비밀번호가 일치하는지 확인
        if(!findUser.getPassword().equals(requestDto.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 이름, 이메일, 비밀번호가 null이 아니면 정보 수정
        Optional.ofNullable(requestDto.getName())
                .ifPresent(findUser::setName);
        Optional.ofNullable(requestDto.getEmail())
                .ifPresent(findUser::setEmail);
        Optional.ofNullable(requestDto.getNewPassword())
                .ifPresent(findUser::setPassword);

        userRepository.save(findUser);

        return new UpdateUserResponseDto(findUser);

    }

    public void deleteUser(Long id, UserRequestDto requestDto){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        User findUser = optionalUser.get();

        // 로그인한 유저의 비밀번호와 입력한 비밀번호가 일치하는지 확인
        if(!findUser.getPassword().equals(requestDto.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        userRepository.deleteById(id);
    }
}
