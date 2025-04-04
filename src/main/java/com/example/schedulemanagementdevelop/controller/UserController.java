package com.example.schedulemanagementdevelop.controller;

import com.example.schedulemanagementdevelop.common.Const;
import com.example.schedulemanagementdevelop.dto.*;
import com.example.schedulemanagementdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        log.info("회원가입 요청: {}", requestDto);
        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.username(),
                        requestDto.password(),
                        requestDto.email()
                );
        log.info("회원가입 응답: {}", signUpResponseDto);
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }
    // 매핑 경로가 {id}로만 하면 겹치기 때문에 한 번씩 더 거쳐주기
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDto> findByUsername(@PathVariable String username) {
        UserResponseDto userResponseDto = userService.findByUsername(username);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable String email) {
        UserResponseDto userResponseDto = userService.findByEmail(email);

        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ) {
        //로그인 유저의 유저id 조회
        LoginResponseDto loginResponseDto = userService.login(dto.email(), dto.password());

        // 로그인 실패 시 로직
        // loginResponsedto.id() == null보다 명확하게 로그인 실패를 명시하는 코드.
        // 어차피 id가 pk값이기 때문에 실행값은 같지만 조건이 더 직관적임
        if (loginResponseDto == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        //조회한 유저 id 선언
        Long userId = loginResponseDto.id();

        // 로그인 성공 시 세션 생성
        HttpSession session = request.getSession();
        // 가져온 id로 유저 정보 조회
        UserResponseDto loginUser = userService.findById(userId);
        // session 내에 가져온 유저 정보를 저장
        session.setAttribute(Const.LOGIN_USER, loginUser);

        // 데이터는 loginUser 가져온 거고, http상태는 정상 출력.
        return new ResponseEntity<>(loginUser, HttpStatus.OK);
    }

    //



    //

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        // 반환할 데이터가 없을 경우, Void 사용 가능.
        // 이후 예외처리 컨트롤러에서 메세지 출력하는 형식으로 따로 다룰 생각.


}
