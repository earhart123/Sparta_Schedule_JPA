package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 정보 저장
     */
    @PostMapping("/create")
    public String createSchedule(@RequestBody ScheduleRequestDto dto){
        return "success";
    }

    /**
     * 전체 일정 조회
     */
    @GetMapping("find-all")
    public String getSchedule(){
        return "success";
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
