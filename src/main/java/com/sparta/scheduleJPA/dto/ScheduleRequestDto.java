package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.User;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private Long user_id;
}
