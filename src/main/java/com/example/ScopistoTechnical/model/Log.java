package com.example.ScopistoTechnical.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @Entity
@NoArgsConstructor
public class Log {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate dateOfExecution;
    Integer numOfBuyers;
    Integer numOfBrowsers;

    public Log(Integer numOfBuyers, Integer numOfBrowsers) {
        this.dateOfExecution = LocalDate.now();
        this.numOfBuyers = numOfBuyers;
        this.numOfBrowsers = numOfBrowsers;
    }
}
