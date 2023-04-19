package com.example.project.controller;

import com.example.project.exception.ForbiddenException;
import com.example.project.exception.NotUniqueEmailException;
import com.example.project.exception.NotUniqueUsernameException;
import com.example.project.model.Manager;
import com.example.project.model.security.User;
import com.example.project.service.ManagerServiceImpl;
import com.example.project.service.RecordLabelServiceImpl;
import com.example.project.service.security.AuthorityService;
import com.example.project.service.security.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.project.configuration.SecurityConfiguration.ROLE_MANAGER;
import static com.example.project.controller.RecordLabelController.BINDING_RESULT_PATH;
import static com.example.project.controller.RecordLabelController.REDIRECT;

@Controller
@RequestMapping("/managers")
@RequiredArgsConstructor
@Slf4j
public class ManagerController {



    private final ManagerServiceImpl managerService;
    private final RecordLabelServiceImpl recordLabelService;
    private final UserService userService;
    private final AuthorityService authorityService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Manager>> getAll() {
        List<Manager> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Manager> getById(@PathVariable("id") Long managerId) {
        Manager manager = managerService.getById(managerId);
        if (manager == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(manager);
        }
    }


    @PutMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Manager> editManager(@PathVariable("id") Long managerId, @RequestBody Manager manager) {
        if (userService.isManager()) {
            if (!userService.checkIfCurrentUserIsSameManager(managerId)) {
                throw new ForbiddenException();
            }
        }

        Manager existingManager = managerService.getById(managerId);

        if (existingManager == null) {
            return ResponseEntity.notFound().build();
        }

        // update the fields of the existing manager with the fields from the request body
        existingManager.setFirstName(manager.getFirstName());
        existingManager.setLastName(manager.getLastName());
        existingManager.setRecordLabel(manager.getRecordLabel());

        Manager updatedManager = managerService.saveManager(existingManager);

        return ResponseEntity.ok(updatedManager);
    }


    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManagerById(id);
        return ResponseEntity.noContent().build();
    }

}
