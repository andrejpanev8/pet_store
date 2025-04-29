package com.example.ScopistoTechnical.controllers;

import com.example.ScopistoTechnical.model.Log;
import com.example.ScopistoTechnical.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/history-log")
public class LogsController {
    private final LogService logService;
    @GetMapping
    public List<Log> getAllLogs(){
        return logService.getAllLogs();
    }
}
