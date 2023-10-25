package com.ciam.cobain.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciam.cobain.dto.response.BaseResponse;

@RestController
@RequestMapping("/")
public class AppController {
    @GetMapping
    public BaseResponse<String> findAllUser() {
        return new BaseResponse<String>(200, "Hi");
    }
}
