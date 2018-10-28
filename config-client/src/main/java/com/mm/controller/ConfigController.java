package com.mm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "config")
public class ConfigController {

    @Value("${hehe}")
    private String hehe;

    @GetMapping(value = "/hehe")
    public String hehe() {
        return hehe;
    }

}
