package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl{
    private final ScheduleRepository scheduleRepository;

    public ScheduleRepositoryImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    //@Override
    public ScheduleResponseDto saveSchedule(String title, String content, User user) {
        Schedule schedule = new Schedule(title, content, user);
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getUser());
    }
}