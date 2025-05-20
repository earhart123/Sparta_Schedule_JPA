package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent(), dto.getUser());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getUser());
    }
}
