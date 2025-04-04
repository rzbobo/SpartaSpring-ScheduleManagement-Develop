package com.example.schedulemanagementdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 생성시간, 수정시간을 사용하기 위한 JpaAuditing 설정
@SpringBootApplication
public class ScheduleManagementDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagementDevelopApplication.class, args);
    }

}
