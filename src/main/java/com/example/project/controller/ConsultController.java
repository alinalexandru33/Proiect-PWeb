package com.example.project.controller;

import com.example.project.exception.CustomException;
import com.example.project.exception.ForbiddenException;
import com.example.project.model.Artist;
import com.example.project.model.Consult;
import com.example.project.model.Deals;
import com.example.project.model.Manager;
import com.example.project.model.dto.SelectedDeals;
import com.example.project.service.ArtistServiceImpl;
import com.example.project.service.ConsultServiceImpl;
import com.example.project.service.DealsServiceImpl;
import com.example.project.service.ManagerServiceImpl;
import com.example.project.service.security.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.project.controller.RecordLabelController.BINDING_RESULT_PATH;
import static com.example.project.controller.RecordLabelController.REDIRECT;

@Controller
@RequestMapping("/consults")
@RequiredArgsConstructor
@Slf4j
public class ConsultController {



    private final ConsultServiceImpl consultService;
    private final ManagerServiceImpl managerService;
    private final DealsServiceImpl dealsService;
    private final ArtistServiceImpl artistService;
    private final UserService userService;




    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") Long consultId) {
        var consult = consultService.getConsultById(consultId);
        var selectedDealsIds = consult.getDeals().stream().map(Deals::getId).collect(Collectors.toList());
        var deals = consult.getDeals().stream()
                .sorted(Comparator.comparing(Deals::getSigningDate).thenComparing(Deals::getContractLength))
                .collect(Collectors.toList());
        var manager = consult.getManager();
        var artist = consult.getArtist();

        var managerName = manager.getLastName() + " " + manager.getFirstName();
        var artistName = artist.getLastName() + " " + artist.getFirstName();

        Map<String, Object> response = new HashMap<>();
        response.put("consult", consult);
        response.put("managerName", managerName);
        response.put("artistName", artistName);
        response.put("dealsAll", deals);
        response.put("selectedDealsIds", selectedDealsIds);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Consult> addConsult(@RequestBody Consult consult) {
        // Set the manager to the current user if they are a manager
        if (UserService.isLoggedIn() && userService.isManager()) {
            consult.setManager(userService.getCurrentUser().getManagers());
        }

        Consult savedConsult = consultService.saveConsult(consult);
        return ResponseEntity.created(URI.create("/consults/" + savedConsult.getId())).body(savedConsult);
    }


    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<Map<String, Object>> editConsult(@PathVariable("id") String consultId, Model model) {
        if (userService.isManager() && !consultService.isMyConsult(Long.valueOf(consultId))) {
            throw new ForbiddenException();
        }
        var managers = managerService.getAllManagers();
        var artist = artistService.getAllArtists();
        List<SelectedDeals> selectedDeals;
        Consult consult;

        /* First time display, no validation failed before */
        if (!model.containsAttribute("consult")) {
            consult = consultService.getConsultById(Long.valueOf(consultId));
            var containedDealsIds = consult.getDeals() == null ? new ArrayList<Long>() : consult.getDeals().stream().map(Deals::getId).collect(Collectors.toList());
            selectedDeals = dealsService.getAllDeals().stream().map(deal -> {
                var isContained = containedDealsIds.contains(deal.getId());
                return new SelectedDeals(deal, isContained);
            }).collect(Collectors.toList());
            model.addAttribute("consult", consult);
        } else {
            consult = (Consult) model.getAttribute("consult");
            var containedDealsIds = consult.getDeals() == null ? new ArrayList<Long>() : consult.getDeals().stream().map(Deals::getId).collect(Collectors.toList());
            selectedDeals = dealsService.getAllDeals().stream().map(deal -> {
                var isContained = containedDealsIds.contains(deal.getId());
                return new SelectedDeals(deal, isContained);
            }).collect(Collectors.toList());
            consult.setDeals(dealsService.findDealsByIdContains(containedDealsIds));
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("selectedDeals", selectedDeals);
        responseData.put("managersAll", managers);
        responseData.put("artistAll", artist);

        /* Managers can edit only their consults */
        if (UserService.isLoggedIn() && userService.isManager()) {
            consult.setManager(userService.getCurrentUser().getManagers());
            var managerName = consult.getManager().getLastName() + " " + consult.getManager().getFirstName();
            responseData.put("isManager", true);
            responseData.put("managerName", managerName);
        } else {
            responseData.put("isManager", false);
        }

        responseData.put("consult", consult);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
    }


        @DeleteMapping("/{id}/delete")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public ResponseEntity<String> deleteConsult(@PathVariable Long id) {
            try {
                consultService.deleteConsultById(id);
                return ResponseEntity.ok().body("Consult with ID " + id + " has been deleted successfully");
             } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting consult with ID " + id);
            }
        }
}
