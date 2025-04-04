package com.example.schedulemanagementdevelop.dto;


/**
 * @param email 비밀번호는 가져올 필요 없음
 */
public record UserResponseDto(String username, String email) {
}