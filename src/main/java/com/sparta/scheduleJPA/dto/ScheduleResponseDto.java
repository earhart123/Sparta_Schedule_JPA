package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private User user;

    public ScheduleResponseDto(Long id, String title, String content, User user){
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }
    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getUser());
    }
}
