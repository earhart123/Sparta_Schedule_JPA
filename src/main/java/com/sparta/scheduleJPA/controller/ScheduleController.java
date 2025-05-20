package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.service.ScheduleService;
import com.sparta.scheduleJPA.service.ScheduleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 정보 저장
     */
    @PostMapping("/create")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.saveSchedule(requestDto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(){
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    /**
     * 단건 일정 조회
     */
    @GetMapping("/{id}")
    public String getScheduleById() {
        return "Success";
    }

    /**
     * 작성자 또는 수정일 기준 일정 조회
     */

    /**
     * 일정 수정
     */

    /**
     * 일정 삭제
     */

    /**
     * 로그인 기능
     * cookie로 로그인 유지
     * 입력 : 아이디, 비밀번호
     */


}
