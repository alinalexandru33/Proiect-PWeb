package com.example.project.service.interfaces;

import com.example.project.model.Song;
import com.example.project.model.dto.ArtistSongDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {

    Page<Song> getAllSongs(Pageable pageable);


    ArtistSongDTO geSongDTOById(Long songId);

    void deleteSongById(Long id);
}
