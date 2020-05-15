package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map<String, String> user, HttpServletResponse response) {
        // DB에 존재하는 user_id 이면 세션 생성
        // 근데 이미 team_id 가 셋팅이 되어있는 상태라면 ?
        Cookie cookie = new Cookie("userId", userService.signin(user.get("userId")));
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
