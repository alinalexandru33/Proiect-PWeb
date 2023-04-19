package com.example.project.controller;

import com.example.project.exception.CustomException;
import com.example.project.model.*;
import com.example.project.model.dto.AlbumSongDTO;
import com.example.project.service.AlbumServiceImpl;
import com.example.project.service.SongServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.example.project.controller.RecordLabelController.*;

@Controller
@RequestMapping("/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumServiceImpl albumService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Album>> getAll() {
        return ResponseEntity.ok().body(albumService.getAllAlbums());
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<String> saveAlbum(@RequestBody Album album) {
        albumService.saveAlbum(album);
        return ResponseEntity.ok("Album saved successfully");
    }



    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<AlbumSongDTO> getById(@PathVariable("id") Long albumId) {
        AlbumSongDTO albumSongDTO = albumService.getAlbumDTOById(albumId);
        return ResponseEntity.ok(albumSongDTO);
    }


    @GetMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbumById(id);
        return ResponseEntity.ok("Album with ID " + id + " has been deleted.");
    }

}
