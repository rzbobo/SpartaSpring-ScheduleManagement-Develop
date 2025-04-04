package com.example.schedulemanagementdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity { // BaseEntity extends 생성 시간, 수정 시간 받아오기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id") // 한 명의 유저가 여러 개의 일정을 작성할 수 있다.
    private User user;

    public Schedule() {
    }

    public Schedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
