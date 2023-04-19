package com.example.project.service.security;

import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.security.User;
import com.example.project.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.project.configuration.SecurityConfiguration.ROLE_MANAGER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new UsernameNotFoundException("Username: " + username);
        }
    }

    public boolean isManager() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            var authorities = (Collection<SimpleGrantedAuthority>) ((UserDetails) principal).getAuthorities();
            return authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList()).contains(ROLE_MANAGER);
        }
        return false;
    }

    public boolean isAdmin() {
        return !isManager();
    }

    public boolean checkIfCurrentUserIsSameManager(Long managerId) {
        return Objects.equals(managerId, getCurrentUser().getManagers().getId());
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            Optional<User> user = userRepository.findByUsername(username);
            if (user.isEmpty()) {
                throw EntityNotFoundException.builder().entityId(null).entityType("User").build();
            }
            return user.get();
        } else throw EntityNotFoundException.builder().entityId(null).entityType("User").build();
    }

    public static boolean isLoggedIn() {
        try {
            var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal == null) {
                return false;
            }
            return principal instanceof UserDetails;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
