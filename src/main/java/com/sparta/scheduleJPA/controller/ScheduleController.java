package com.sparta.scheduleJPA.controller;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    void createSchedule(@RequestBody ScheduleRequestDto dto){

    }

    /**
     * 단건 일정 조회
     */

    /**
     * 전체 일정 조회
     */

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
