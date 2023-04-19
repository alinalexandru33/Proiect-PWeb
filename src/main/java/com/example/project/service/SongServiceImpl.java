package com.example.project.service;

import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Artist;
import com.example.project.model.Song;
import com.example.project.model.dto.ArtistSongDTO;
import com.example.project.repository.SongRepository;
import com.example.project.service.interfaces.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Page<Song> getAllSongs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }


    public ArtistSongDTO geSongDTOById(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new EntityNotFoundException("Song not found", songId));
        Artist artist = song.getAlbum().getArtist(); // Get the artist from the song
        ArtistSongDTO artistSongDTO = new ArtistSongDTO();
        artistSongDTO.setSongId(song.getSongId());
        artistSongDTO.setSongTitle(song.getSongTitle());
        artistSongDTO.setSongLength(song.getSongLength());
        artistSongDTO.setLanguage(song.getLanguage());
        artistSongDTO.setArtist(artist); // Set the artist in the DTO
        return artistSongDTO;
    }



    public Song saveSong(Song song) {
        return songRepository.save(song);
    }


    public void deleteSongById(Long id) {
        songRepository.deleteById(id);
    }
}
