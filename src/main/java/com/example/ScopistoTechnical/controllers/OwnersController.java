package com.example.ScopistoTechnical.controllers;

import com.example.ScopistoTechnical.model.AppUser;
import com.example.ScopistoTechnical.service.OwnerService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@AllArgsConstructor
@RequestMapping({"/api/owners", "/api/"})
public class OwnersController {
    private final OwnerService ownerService;

    @GetMapping
    public ResponseEntity<List<AppUser>> listAllOwners() throws Exception {
        List<AppUser> owners = ownerService.getAllOwners();
        return ResponseEntity.ok(owners);
    }
}
