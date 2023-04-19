package com.example.project.service;

import com.example.project.exception.CustomException;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Album;
import com.example.project.model.Song;
import com.example.project.model.dto.AlbumSongDTO;
import com.example.project.repository.AlbumRepository;
import com.example.project.repository.ArtistRepository;
import com.example.project.repository.SongRepository;
import com.example.project.service.interfaces.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    private final SongRepository songRepository;


    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public AlbumSongDTO getAlbumDTOById(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found", albumId));
        List<Song> songs = songRepository.findSongsByAlbum(album);
        AlbumSongDTO albumSongDTO = new AlbumSongDTO();
        albumSongDTO.setAlbumId(album.getAlbumId());
        albumSongDTO.setAlbumName(album.getAlbumName());
        albumSongDTO.setAlbumYear(album.getAlbumYear());
        albumSongDTO.setNoTracks(album.getNoTracks());
        albumSongDTO.setSongs(songs);
        return albumSongDTO;
    }

    public void saveAlbum(Album album) {
        albumRepository.save(album);
    }

    public void deleteAlbumById(Long albumId) {
        albumRepository.deleteById(albumId);
    }
}
