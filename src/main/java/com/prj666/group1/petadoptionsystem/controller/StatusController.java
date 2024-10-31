package com.prj666.group1.petadoptionsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class StatusController {

    @GetMapping("/status")
    public String getStatus() throws SQLException {
        return "Application is running";
    }
}