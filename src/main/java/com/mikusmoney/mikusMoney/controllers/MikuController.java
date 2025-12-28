package com.mikusmoney.mikusMoney.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v0.1/miku/")
@RequiredArgsConstructor
public class MikuController {
    
    @GetMapping("mikuHi")
    public String miku() {
        return "Miku is here!";
    }
    

}
