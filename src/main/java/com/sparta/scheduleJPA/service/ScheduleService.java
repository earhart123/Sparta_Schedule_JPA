package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;

public interface ScheduleService {
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);

}
