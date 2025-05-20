package com.sparta.scheduleJPA.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String email;
    private final String password;

    public SignUpResponseDto(Long id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
