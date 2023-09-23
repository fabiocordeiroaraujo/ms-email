package com.fabioaraujo.ms.email.entrypoint.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms/hello")
public class MsRestController {
    @GetMapping
    public String doYouHearMe(){
        return "I Hear you 🥰";
    }
}
