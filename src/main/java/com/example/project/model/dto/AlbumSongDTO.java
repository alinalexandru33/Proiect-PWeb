package com.example.project.model.dto;

import com.example.project.model.Song;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;


@Data
public class AlbumSongDTO {

    private Long albumId;

    @NotBlank(message = "Album name must be provided!")
    private String albumName;

    @NotNull(message = "Album year must be provided!")
    private int albumYear;

    @NotNull(message = "Number of tracks must be provided!")
    private int noTracks;

    private List<Song> songs;
}
