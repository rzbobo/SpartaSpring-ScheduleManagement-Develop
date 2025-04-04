package com.example.schedulemanagementdevelop.repository;

import com.example.schedulemanagementdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface    ScheduleRepository extends JpaRepository<Schedule, Long> {
}
