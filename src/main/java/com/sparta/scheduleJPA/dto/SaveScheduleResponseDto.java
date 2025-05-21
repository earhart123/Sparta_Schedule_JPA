package com.sparta.scheduleJPA.dto;

import lombok.Getter;

@Getter
public class SaveScheduleResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String userName;

    public SaveScheduleResponseDto(Long id, String title, String content, Long userId, String userName){
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
    }
}
