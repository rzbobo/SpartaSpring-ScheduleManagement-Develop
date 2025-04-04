package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.ScheduleResponseDto;
import com.example.schedulemanagementdevelop.entity.Schedule;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.ScheduleRepository;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 일정 생성
    public ScheduleResponseDto save(String title, String contents, String username) {
        User findUser = userRepository.findUserByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findUser);

        scheduleRepository.save(schedule);

        return  new ScheduleResponseDto(schedule.getId(),schedule.getTitle(),schedule.getContents());
    }

    // 일정 조회(전체)
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }


}
