package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;

public interface ScheduleRepository {
    ScheduleRequestDto saveSchedule(ScheduleResponseDto dto);
}
