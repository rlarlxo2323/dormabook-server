package com.dormabook.web.controller.member;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("dashboard")
    public String dashboard() {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("=========================");
        System.out.println(memberId);
        System.out.println("=========================");
        return "dashboard";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
