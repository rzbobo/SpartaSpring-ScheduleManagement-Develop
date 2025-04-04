package com.example.schedulemanagementdevelop.filter;


import com.example.schedulemanagementdevelop.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

//로그인 유무 확인하는 필터
// 중요 ! 필터는 한번 만들어두면 각 컨트롤러에서 적용하는 개념이 아니라
// 전역으로 적용되는 개념임. 이를 WebConfig에서 설정하는 것.
@Slf4j
public class LoginFilter implements Filter {


    // LoginFilter를 거치지 않는 URL 특정
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login", "/users/logout"};

    // init, destory는 default로 구현되어 있음
    // 따라서, doFilter 메서드만 구현
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실패");

        //whiteList에 존재하는 URL인지 검사
        if (!isWhiteList(requestURI)) {
            // 로그인
            HttpSession session = httpRequest.getSession(false);
            if(session == null || session.getAttribute(Const.LOGIN_USER)== null) {
                throw new RuntimeException("로그인하세요.");
            }
            //else 로그인 성공
        }
        filterChain.doFilter(request,response);
    }

    // 로그인 필터 사용하는 URL인지 검사하는 메서드
    private  boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }


}
