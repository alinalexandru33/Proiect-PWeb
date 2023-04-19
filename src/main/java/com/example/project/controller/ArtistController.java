package com.example.project.controller;

import com.example.project.exception.CustomException;
import com.example.project.model.Address;
import com.example.project.model.Artist;
import com.example.project.service.ArtistServiceImpl;
import com.example.project.service.RecordLabelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.project.controller.RecordLabelController.BINDING_RESULT_PATH;
import static com.example.project.controller.RecordLabelController.REDIRECT;

@Controller
@RequestMapping("/artists")
@RequiredArgsConstructor
@Slf4j
public class ArtistController {


    private final ArtistServiceImpl artistService;
    private final RecordLabelServiceImpl recordLabelService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Artist>> getAll() {
        List<Artist> artists = artistService.getAllArtists();
        return ResponseEntity.ok(artists);
    }


    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Artist> getById(@PathVariable("id") Long artistId) {
        Artist artist = artistService.getArtistById(artistId);
        if (artist != null) {
            return ResponseEntity.ok(artist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addArtist(@RequestBody Artist artist) {
        // save the new artist object to the database
        artist = artistService.saveArtist(artist);

        // return a response with the saved artist object and a location header
        return ResponseEntity.created(URI.create("/artists/" + artist.getId())).body(artist);
    }


    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtistById(id);
        return ResponseEntity.ok("Artist with ID " + id + " was deleted.");
    }



}
