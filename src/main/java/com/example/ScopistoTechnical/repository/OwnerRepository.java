package com.example.ScopistoTechnical.repository;

import com.example.ScopistoTechnical.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<AppUser, Long> {
}
