package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private User user;
}
