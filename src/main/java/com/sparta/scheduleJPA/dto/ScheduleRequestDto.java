package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.User;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String content;
    private User user;
}
