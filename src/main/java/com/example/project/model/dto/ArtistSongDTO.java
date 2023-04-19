package com.example.project.model.dto;

import com.example.project.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistSongDTO {

    @NotNull
    private Long songId;

    @NotBlank
    @Size(max = 255)
    private String songTitle;

    @NotNull
    private String songLength;

    @NotNull
    private String language;

    private Artist artist;


}
