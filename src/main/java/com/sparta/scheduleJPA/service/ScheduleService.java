package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    public List<ScheduleResponseDto> findAll();
    public ScheduleResponseDto findById(Long id);
    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto requestDto);
    public void deleteSchedule(Long id);
}
