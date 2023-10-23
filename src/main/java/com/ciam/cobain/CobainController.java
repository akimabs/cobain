package com.ciam.cobain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CobainController {
    @RequestMapping("/")
    public String hello() {
        return "Hello World";
    }
}
