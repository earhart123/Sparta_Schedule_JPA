package com.sparta.scheduleJPA.service;

import com.sparta.scheduleJPA.dto.ScheduleRequestDto;
import com.sparta.scheduleJPA.dto.ScheduleResponseDto;
import com.sparta.scheduleJPA.dto.SaveScheduleResponseDto;
import com.sparta.scheduleJPA.entity.Schedule;
import com.sparta.scheduleJPA.entity.User;
import com.sparta.scheduleJPA.repository.ScheduleRepository;
import com.sparta.scheduleJPA.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public SaveScheduleResponseDto saveSchedule(ScheduleRequestDto dto, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User findUser = optionalUser.get();

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent(), findUser);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new SaveScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent(),
                savedSchedule.getUser().getId(), schedule.getUser().getName());
    }

    public List<ScheduleResponseDto> findAll(Long userId){
        return scheduleRepository.findUserScheduleAll();
    }

    public ScheduleResponseDto findById(Long id, Long userId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }
        Schedule findSchedule = optionalSchedule.get();

        // 현재 로그인한 유저가 등록한 일정을 수정하는지 확인
        if(Objects.equals(findSchedule.getUser().getId(), userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 정보가 잘못되었습니다.");
        }

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContent(),
                findSchedule.getCreatedAt(), findSchedule.getModifiedAt(),
                findSchedule.getUser().getId(), findSchedule.getUser().getName());
    }

    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto requestDto, Long userId){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        Schedule findSchedule = optionalSchedule.get();

//        if(requestDto.getTitle()!=null){
//            findSchedule.setTitle(requestDto.getTitle());
//        }
//        if(requestDto.getContent()!=null){
//            findSchedule.setContent(requestDto.getContent());
//        }

        // 현재 로그인한 유저가 등록한 일정을 수정하는지 확인
        if(Objects.equals(findSchedule.getUser().getId(), userId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 정보가 잘못되었습니다.");
        }

        Optional.ofNullable(requestDto.getTitle())
                .ifPresent(findSchedule::setTitle);

        Optional.ofNullable(requestDto.getContent())
                .ifPresent(findSchedule::setContent);

        scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContent(),
                findSchedule.getCreatedAt(), findSchedule.getModifiedAt(),
                findSchedule.getUser().getId(), findSchedule.getUser().getName());
    }

    public void deleteSchedule(Long id){
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id =" + id);
        }

        Schedule findSchedule = optionalSchedule.get();

        scheduleRepository.delete(findSchedule);
    }
}
