package org.nayutablogserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping
    public String test() {
        return  "test";
    }

}
