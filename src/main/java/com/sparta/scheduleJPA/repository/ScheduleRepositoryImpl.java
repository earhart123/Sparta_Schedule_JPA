package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    @PersistenceContext
    private EntityManager em;


    @Override
    public ScheduleRequestDto saveSchedule(ScheduleResponseDto dto) {
        em.persist(dto);
        return null;
    }
}