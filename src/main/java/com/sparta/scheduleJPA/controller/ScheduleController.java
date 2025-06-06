package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.*;
import com.sparta.scheduleJPA.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
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
     * 일정 저장
     * @param requestDto
     * { title, content, user }
     * @return ScheduleResponseDto
     */
    @PostMapping("/create")
    public ResponseEntity<SaveScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto, HttpSession session){

        Long userId = ((Long) session.getAttribute("userKey"));

        SaveScheduleResponseDto saveScheduleResponseDto =
                scheduleService.saveSchedule(requestDto, userId);

        return new ResponseEntity<>(saveScheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회
     * @return
     * List<ScheduleResponseDto>
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(HttpSession session){
        Long userId = ((Long) session.getAttribute("userKey"));

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll(userId);
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    /**
     * 단건 일정 조회
     * @param { scheduleId }
     * @return
     * { ScheduleResponseDto }
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id, HttpSession session) {
        Long userId = ((Long) session.getAttribute("userKey"));

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id, userId);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    /**
     * 일정 수정
     * @param { scheduleId }
     * @param requestDto
     * { title, content }
     * @return ScheduleResponseDto
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> editSchedule(@PathVariable Long id,
                                                            @RequestBody ScheduleRequestDto requestDto,
                                                            HttpSession session){
        Long userId = ((Long) session.getAttribute("userKey"));

        ScheduleResponseDto scheduleResponseDto = scheduleService.editSchedule(id, requestDto, userId);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session){
        //Long userId = ((Long) session.getAttribute("userKey"));
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
