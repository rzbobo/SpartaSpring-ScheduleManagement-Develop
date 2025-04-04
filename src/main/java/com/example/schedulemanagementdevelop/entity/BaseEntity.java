package com.example.schedulemanagementdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
// 생성 시간, 수정 시간을 다루는 Entity
// 추상 클래스로 만들어 놓고, User와 Schedule Entity에 주입하는 방식
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
// @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // 생성 시간

    @LastModifiedDate
    private LocalDateTime modifiedAt; // 수정 시간
}
