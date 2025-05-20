package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.SignUpRequestDto;
import com.sparta.scheduleJPA.dto.SignUpResponseDto;
import com.sparta.scheduleJPA.dto.UpdateUserRequestDto;
import com.sparta.scheduleJPA.dto.UserResponseDto;
import com.sparta.scheduleJPA.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     *
     * @param requestDto
     * { name, email, password }
     * @return
     * ResponseEntity
     */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto = userService.signUp(
                requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    /**
     * 유저 조회
     *
     * @param id
     * @return
     * ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUserInfo(@PathVariable Long id,
                                                          @RequestBody UpdateUserRequestDto requestDto){
        UserResponseDto userResponseDto = userService.updateUserInfo(id, requestDto.getName(), requestDto.getEmail(), requestDto.getOldPassword(), requestDto.getNewPassword());

    }
}
