package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import com.sparta.scheduleJPA.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT new com.sparta.scheduleJPA.dto.ScheduleResponseDto(s.id, s.title, s.content, u.id, u.name) " +
            "FROM Schedule s JOIN s.user u")
    List<ScheduleResponseDto> findUserScheduleAll();
}
