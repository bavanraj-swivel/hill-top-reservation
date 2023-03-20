package com.hilltop.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AwsHealthController {

    /**
     * Used to check if hosted service is running.
     *
     * @return message string.
     */
    @GetMapping("/")
    public String ping() {
        return "This is hill-top-reservation service !!!";
    }
}
