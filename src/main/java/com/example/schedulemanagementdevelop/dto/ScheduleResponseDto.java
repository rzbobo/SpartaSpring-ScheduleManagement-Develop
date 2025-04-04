package com.example.schedulemanagementdevelop.dto;

import com.example.schedulemanagementdevelop.entity.Schedule;

public record ScheduleResponseDto (Long id, String title, String contents){




    // Entity를 Dto로 변환하는 작업
    // toDto라는 명칭으로 가지고 다님
    public static ScheduleResponseDto toDto(Schedule schedule){
        return  new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents());
    }
}