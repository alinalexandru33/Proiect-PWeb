package com.example.project.service.interfaces;

import com.example.project.model.Consult;
import com.example.project.model.Manager;
import com.example.project.model.security.User;

import java.util.List;

public interface ManagerService {

    List<Manager> getAllManagers();

    List<Consult> getAllConsultsForManager(Long managerId);

    Manager getById(Long id);

    User saveOrUpdateUser(User user, Manager manager, String password);

    Manager saveManager(Manager manager);

    void deleteManagerById(Long id);
}
