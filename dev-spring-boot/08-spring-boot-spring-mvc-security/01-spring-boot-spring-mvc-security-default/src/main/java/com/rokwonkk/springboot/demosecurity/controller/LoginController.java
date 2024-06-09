package com.rokwonkk.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
//        return "plain-login";
        return "fancy-login";
    }

    // add request mapping for /access-denied
    @GetMapping("/access-denied") // access-denied 경로에 대한 요청을 처리
    public String showAccessDenied() { // 접근 거부 페이지를 보여주는 메소드
        return "access-denied";
    }
}