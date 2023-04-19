package com.example.project.bootstrap;

import com.example.project.model.Manager;
import com.example.project.model.security.Authority;
import com.example.project.model.security.User;
import com.example.project.repository.security.AuthorityRepository;
import com.example.project.repository.security.UserRepository;
import com.example.project.service.ManagerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.example.project.configuration.SecurityConfiguration.*;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ManagerServiceImpl managerService;

    private void loadUserData() {
        if (userRepository.count() == 0) {
            Authority adminRole = authorityRepository.save(Authority.builder().role(ROLE_ADMIN).build());
            Authority managerRole = authorityRepository.save(Authority.builder().role(ROLE_MANAGER).build());

            User admin = User.builder()
                    .username("admin_1")
                    .password(passwordEncoder.encode("123456"))
                    .email("admin_1@email.com")
                    .authority(adminRole)
                    .build();

            Manager managerId1 = managerService.getById(1L);
            Manager managerId2 = managerService.getById(2L);
            Manager managerId3 = managerService.getById(3L);

            User manager1 = User.builder()
                    .username("manager_1")
                    .password(passwordEncoder.encode("123456"))
                    .managers(managerId1)
                    .email("manager1@email.com")
                    .authority(managerRole)
                    .build();

            User manager2 = User.builder()
                    .username("manager_2")
                    .password(passwordEncoder.encode("123456"))
                    .managers(managerId2)
                    .email("manager2@email.com")
                    .authority(managerRole)
                    .build();

            User manager3 = User.builder()
                    .username("manager_3")
                    .password(passwordEncoder.encode("123456"))
                    .managers(managerId3)
                    .email("manager3@email.com")
                    .authority(managerRole)
                    .build();

            managerId1.setUser(manager1);
            managerId2.setUser(manager2);
            managerId3.setUser(manager3);

            userRepository.save(admin);
            userRepository.save(manager1);
            userRepository.save(manager2);
            userRepository.save(manager3);

            managerService.saveManager(managerId1);
            managerService.saveManager(managerId2);
            managerService.saveManager(managerId3);
        }
    }


    @Override
    public void run(String... args) {
        loadUserData();
    }
}
