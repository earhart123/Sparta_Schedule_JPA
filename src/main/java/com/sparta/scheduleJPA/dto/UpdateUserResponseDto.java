package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.User;
import lombok.Getter;

@Getter
public class UpdateUserResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;

    public UpdateUserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
