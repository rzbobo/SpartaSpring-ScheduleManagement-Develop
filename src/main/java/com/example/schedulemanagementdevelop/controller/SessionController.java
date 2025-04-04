package com.example.schedulemanagementdevelop.controller;


import com.example.schedulemanagementdevelop.common.Const;
import com.example.schedulemanagementdevelop.dto.LoginRequestDto;
import com.example.schedulemanagementdevelop.dto.LoginResponseDto;
import com.example.schedulemanagementdevelop.dto.UserResponseDto;
import com.example.schedulemanagementdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private final UserService userService;

    @GetMapping("/home")
    public String home(
            HttpServletRequest request,
            Model model
    ) {

        // default인 true로 설정될 시,
        // 로그인하지 않은 사람들도 값이 비어있지만 세션이 만들어지는 걸 방지
        // session 생성 의도 x
        HttpSession session = request.getSession(false);

        // session이 존재하지 않으면, 로그인 페이지 이동
        if (session == null) {
            return "session/login";
        }
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        // session에 유저 정보가 없으면 login 페이지 이동

        if (loginUser == null) {
            return "session-login";
        }
        // 세션 정상 조회 시, 로그인 성공
        // home으로 이동
        model.addAttribute("loginUser", loginUser);
        return "session-home";
    }
}
    // 궁금증 : 왜 login과 logout에서는 redirect를 진행하고 home에서는 바로 return해주나?
    // 예상 답안 : 로그인과 로그아웃은 특정 버튼을 누름으로써 다음 페이지로 이동하는 과정에서 그런 로직이 발생
    // home은 home으로 들어가는 과정 자체에서의 로직이기 때문
