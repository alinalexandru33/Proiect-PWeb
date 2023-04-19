package com.example.project.service.interfaces;

import com.example.project.model.Album;
import com.example.project.model.dto.AlbumSongDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {
    List<Album> getAllAlbums();

    AlbumSongDTO getAlbumDTOById(Long albumId);

    void saveAlbum(Album album);

    void deleteAlbumById(Long albumId);
}
