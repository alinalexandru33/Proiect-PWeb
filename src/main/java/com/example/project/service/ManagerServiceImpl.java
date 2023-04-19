package com.example.project.service;

import com.example.project.exception.EntityNotFoundException;
import com.example.project.exception.NotUniqueEmailException;
import com.example.project.exception.NotUniqueUsernameException;
import com.example.project.model.Consult;
import com.example.project.model.Manager;
import com.example.project.model.security.User;
import com.example.project.repository.ManagerRepository;
import com.example.project.service.interfaces.ManagerService;
import com.example.project.service.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.project.service.security.UserService.isLoggedIn;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ConsultServiceImpl consultService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public List<Consult> getAllConsultsForManager(Long managerId) {
        return consultService.getConsultsByManagerId(managerId);
    }

    public Manager getById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(id)
                        .entityType("Manager")
                        .build());
    }

    public User saveOrUpdateUser(User user, Manager manager, String password) {

        var userUsername = userService.getUserByUsername(user.getUsername());
        var userEmail = userService.getUserByEmail(user.getEmail());

        if (userUsername.isPresent() && !Objects.equals(userUsername.get().getIdentifier(), user.getIdentifier())) {
            throw new NotUniqueUsernameException(String.format("Username %s already exists!", user.getUsername()));
        }

        if (userEmail.isPresent() && !Objects.equals(userEmail.get().getEmail(), user.getEmail())) {
            throw new NotUniqueEmailException(String.format("Email %s already exists!", user.getEmail()));
        }

        manager.setConsults(getAllConsultsForManager(manager.getId()));

        if (!isLoggedIn()) {
            // register form
            manager.setUser(user);
            user.setManagers(manager);
            return saveManager(manager).getUser();

        }
        if (userService.isAdmin()) {
            // admins can't change passwords
            var managerInDB = getById(user.getManagers().getId());
            var userInDB =managerInDB.getUser();

            managerInDB.setFirstName(manager.getFirstName());
            managerInDB.setLastName(manager.getLastName());
            managerInDB.setRecordLabel(manager.getRecordLabel());

            userInDB.setUsername(user.getUsername());
            userInDB.setEmail(user.getEmail());

            managerInDB.setUser(userInDB);
            userInDB.setManagers(managerInDB);

            return saveManager(managerInDB).getUser();

        } else {
            // only managers can change their own passwords
            if ("".equals(password)) {
                // password not provided - then don't change it
                var currentPassword = userService.getCurrentUser().getPassword();
                user.setPassword(currentPassword);
            } else {
                // password provided. change it
                var encodedPassword = passwordEncoder.encode(password);
                user.setPassword(encodedPassword);
            }

            var managerInDB = getById(user.getManagers().getId());
            var userInDB = managerInDB.getUser();

            managerInDB.setFirstName(manager.getFirstName());
            managerInDB.setLastName(manager.getLastName());
            managerInDB.setRecordLabel(manager.getRecordLabel());

            userInDB.setEmail(user.getEmail());
            userInDB.setPassword(user.getPassword());

            managerInDB.setUser(userInDB);
            userInDB.setManagers(managerInDB);


            return saveManager(managerInDB).getUser();
        }
    }

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteManagerById(Long id) {
        managerRepository.deleteById(id);
    }
}
