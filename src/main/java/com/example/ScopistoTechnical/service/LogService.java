package com.example.ScopistoTechnical.service;

import com.example.ScopistoTechnical.model.Log;

import java.util.List;

public interface LogService {
    public Log recordBuyExecution(Log log);
    public List<Log> getAllLogs();
}
