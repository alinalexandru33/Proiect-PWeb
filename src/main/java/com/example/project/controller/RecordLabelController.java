package com.example.project.controller;

import com.example.project.exception.CustomException;
import com.example.project.model.Album;
import com.example.project.model.RecordLabel;
import com.example.project.service.RecordLabelServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Setter
@RequestMapping("/recordlabels")
@NoArgsConstructor
@Slf4j
public class RecordLabelController {
    public final static String REDIRECT = "redirect:/";
    public final static String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    public final static String ALL_RECORDLABELS = "recordlabels";
    private final static String VIEW_RECORDLABEL = "recordlabel_info";
    private final static String ADD_EDIT_RECORDLABEL = "recordlabel_form";

    @Autowired
    private RecordLabelServiceImpl recordLabelService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<RecordLabel>> getAll() {
        return ResponseEntity.ok().body(recordLabelService.getAllRecordLabels());
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RecordLabel> getById(@PathVariable("id") String recordLabelId) {
        var recordLabel = recordLabelService.getRecordLabelById(Long.valueOf(recordLabelId));
        return ResponseEntity.ok(recordLabel);
    }



}
