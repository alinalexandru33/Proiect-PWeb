package com.example.project.controller;

import com.example.project.exception.CustomException;
import com.example.project.model.Deals;
import com.example.project.model.RecordLabel;
import com.example.project.model.dto.ArtistSongDTO;
import com.example.project.service.DealsServiceImpl;
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
import java.util.Map;

import static com.example.project.controller.RecordLabelController.BINDING_RESULT_PATH;
import static com.example.project.controller.RecordLabelController.REDIRECT;

@Controller
@RequestMapping("/deals")
@RequiredArgsConstructor
@Slf4j
public class DealsController {

    private final static String ALL_DEALS = "deals";
    private final static String VIEW_DEAL = "deal_info";
    private final static String ADD_EDIT_DEAL = "deal_form";

    private final DealsServiceImpl dealsService;


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Deals> getById(@PathVariable("id") String dealsIs) {
        var deals = dealsService.getDealsById(Long.valueOf(dealsIs));
        return ResponseEntity.ok(deals);
    }


}
