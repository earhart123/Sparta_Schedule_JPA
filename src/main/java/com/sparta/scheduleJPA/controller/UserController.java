package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.*;
import com.sparta.scheduleJPA.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
     *
     * @param requestDto
     * { email, password }
     * @param session
     * @return
     * UserResponseDto
     */
//    @PostMapping("/login")
//    public UserResponseDto logIn(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){
//        LoginResponseDto responseDto = userService.login(requestDto.getEmail(), requestDto.getPassword());
//        Long userId = responseDto.getId();
//        UserResponseDto loginUser = userService.findById(responseDto.getId());
//
//        HttpSession session = request.getSession();
//
//        // 세션에 로그인 회원 정보 저장
//        //session.setAttribute("loginUser", loginUser);
//        session.setAttribute("userKey", userId);
//
//        return userService.findById(responseDto.getId());
//    }

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
     * @param id
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
    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserResponseDto>> findById(@PathVariable String name){
        List<UserResponseDto> userResponseDtoList = userService.findByName(name);
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }



    @PatchMapping("/edit")
    public ResponseEntity<UpdateUserResponseDto> updateUserInfo(@RequestBody UpdateUserRequestDto requestDto,
                                                          HttpSession session){
        Long userId = ((Long) session.getAttribute("userKey"));

        UpdateUserResponseDto updateUserResponseDto = userService.updateUserInfo(userId, requestDto);

        return new ResponseEntity<>(updateUserResponseDto, HttpStatus.OK);
    }
}
