package com.sparta.scheduleJPA.repository;

import com.sparta.scheduleJPA.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
