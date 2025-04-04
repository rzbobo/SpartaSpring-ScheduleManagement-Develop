package com.example.schedulemanagementdevelop.service;

import com.example.schedulemanagementdevelop.dto.LoginResponseDto;
import com.example.schedulemanagementdevelop.dto.SignUpResponseDto;
import com.example.schedulemanagementdevelop.dto.UserResponseDto;
import com.example.schedulemanagementdevelop.entity.User;
import com.example.schedulemanagementdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //생성
    public SignUpResponseDto signUp(String username, String password, String email) {
        User user = new User(username, password, email);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }


    // 유저 id 기반 조회
    public UserResponseDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        // NPE 방지
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }
    // 유저 email 기반 조회
    public UserResponseDto findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);

        // NPE 방지
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email = " + email);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    // 유저 이름 기반 조회
    public UserResponseDto findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        // NPE 방지
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    // 로그인
    public LoginResponseDto login(String email, String password) {
        Optional<User> optionalUser = userRepository.findIdByEmailAndPassword(email, password);

        // - [ ]  **예외처리**
        //    - [ ]  로그인 시 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환합니다.
        // 비어 있다는 건 일치하지 않다는 것(일치해야 데이터 가져오니까)
        // 401 Error -> UNAUTORIZED
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        User user = optionalUser.get();

        return new LoginResponseDto(user.getId());
    }
}
    //


