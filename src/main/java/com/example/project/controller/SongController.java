package com.example.project.controller;

import com.example.project.model.dto.ArtistSongDTO;
import com.example.project.service.SongServiceImpl;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Setter
@RequestMapping("/songs")
@NoArgsConstructor
@Slf4j
public class SongController {

    @Autowired
    private SongServiceImpl songService;



    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<ArtistSongDTO> getById(@PathVariable("id") Long songId) {
        ArtistSongDTO artistSongDTO = songService.geSongDTOById(songId);
        return ResponseEntity.ok().body(artistSongDTO);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);
        return ResponseEntity.ok("Song with ID " + id + " has been deleted.");
    }


}
