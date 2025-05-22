package com.sparta.scheduleJPA.dto;

import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long userId;
    private String userName;

    public ScheduleResponseDto(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, Long userId, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
    }

//    public static ScheduleResponseDto toDto(Schedule schedule, Long userId, String userName){
//        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent(), userId, userName);
//    }
}
