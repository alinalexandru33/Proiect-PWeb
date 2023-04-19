package com.example.project.service.security;

import com.example.project.model.security.Authority;
import com.example.project.repository.security.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public Authority getByRole(String role) {
        var authority = authorityRepository.getByRole(role);
        if (Objects.isNull(authority)) {
            throw new RuntimeException("Role not found!");
        }
        return authority;
    }

}
