package com.example.project.repository.security;

import com.example.project.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Authority getByRole(String role);

    Optional<Authority> findByRole(String name);

}
