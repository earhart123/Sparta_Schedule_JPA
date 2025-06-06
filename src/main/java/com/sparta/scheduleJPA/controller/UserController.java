package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.*;
import com.sparta.scheduleJPA.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto = userService.signUp(
                requestDto.getName(), requestDto.getEmail(), requestDto.getPassword());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    /**
     * 로그인
     *
     * @param requestDto
     * { email, password }
     * @param session
     * { userId }
     * @return
     * UserResponseDto
     */
    @PostMapping("/login")
    public UserResponseDto logIn(@Valid @RequestBody LoginRequestDto requestDto, HttpSession session){

        LoginResponseDto responseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());
        Long userId = responseDto.getId();

        // 세션에 로그인한 유저 id 저장
        session.setAttribute("userKey", userId);

        return userService.findById(responseDto.getId());
    }

    /**
     * id로 유저 조회
     *
     * @param { userId }
     * @return
     * ResponseEntity
     */
    @GetMapping("/search/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    /**
     * name으로 유저 리스트 조회
     *
     * @param name
     * @return
     * List<UserResponseDto>
     */
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDto>> findById(@RequestParam String name){
        List<UserResponseDto> userResponseDtoList = userService.findByName(name);
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }


    /**
     * 유저 정보 수정
     *
     * @param requestDto
     * { name, email, oldPassword, newPassword }
     * @param { 로그인 session }
     * @return { UpdateUserResponseDto }
     */
    @PatchMapping("/edit")
    public ResponseEntity<UpdateUserResponseDto> updateUserInfo(@RequestBody UpdateUserRequestDto requestDto,
                                                          HttpSession session){
        Long userId = ((Long) session.getAttribute("userKey"));

        UpdateUserResponseDto updateUserResponseDto = userService.updateUserInfo(userId, requestDto);

        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }

    /**
     * 유저 삭제
     *
     * @param requestDto
     * { password }
     * @param { 로그인 session }
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestBody UserRequestDto requestDto, HttpSession session){

        Long userId = ((Long) session.getAttribute("userKey"));

        userService.deleteUser(userId, requestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
