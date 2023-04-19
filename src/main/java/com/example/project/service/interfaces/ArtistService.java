package com.example.project.service.interfaces;

import com.example.project.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();

    Artist getArtistById(Long artistId);

    Artist saveArtist(Artist artist);

    Artist deleteArtistById(Long id);
}
