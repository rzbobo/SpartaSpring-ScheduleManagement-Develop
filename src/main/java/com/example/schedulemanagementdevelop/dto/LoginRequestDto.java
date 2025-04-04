package com.example.schedulemanagementdevelop.dto;

// 로그인 시, 필요한 데이터는 email, password
public record LoginRequestDto (String email, String password) {
}
