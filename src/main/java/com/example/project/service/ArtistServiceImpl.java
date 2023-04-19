package com.example.project.service;

import com.example.project.exception.CustomException;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Artist;
import com.example.project.model.Consult;
import com.example.project.repository.ArtistRepository;
import com.example.project.service.interfaces.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(artistId)
                        .entityType("Artist")
                        .build());
    }

    public Artist saveArtist(Artist artist) {

        var existingArtist = artistRepository.getByCnp(artist.getCnp());
        if (existingArtist.isEmpty()) {
            return artistRepository.save(artist);
        }

        if (artist.getId() == null) {
            // save flow
            if (existingArtist.get().getCnp().equals(artist.getCnp())) {
                throw new CustomException(String.format("CNP %s already taken!", artist.getCnp()));
            }
        } else {
            // edit flow
            if (existingArtist.get().getCnp().equals(artist.getCnp()) && !existingArtist.get().getId().equals(artist.getId())) {
                throw new CustomException(String.format("CNP %s already taken!", artist.getCnp()));
            }
        }

        return artistRepository.save(artist);
    }

    public Artist deleteArtistById(Long id) {
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            artistRepository.deleteById(id);
            return optionalArtist.get();
        } else {
            throw new EntityNotFoundException("Artist", id);
        }
    }


}
