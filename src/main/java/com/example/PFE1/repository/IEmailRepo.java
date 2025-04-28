package com.example.PFE1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PFE1.model.Email;





public interface IEmailRepo extends JpaRepository<Email, Long> {
}
