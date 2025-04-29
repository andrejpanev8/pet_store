package com.example.ScopistoTechnical.service.impl;

import com.example.ScopistoTechnical.model.Log;
import com.example.ScopistoTechnical.repository.LogRepository;
import com.example.ScopistoTechnical.service.LogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    @Override
    public Log recordBuyExecution(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}
