package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.SaveScheduleResponseDto;
import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(ScheduleRequestDto dto, Long userId);
    List<ScheduleResponseDto> findAll(Long userId);
    ScheduleResponseDto findById(Long id, Long userId);
    ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto requestDto, Long userId);
    void deleteSchedule(Long id);
}
