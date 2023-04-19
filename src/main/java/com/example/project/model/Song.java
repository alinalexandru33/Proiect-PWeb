package com.example.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import static com.example.project.model.Regex.LENGTH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_song")
    private Long songId;

    @Column(name="song_title")
    private String songTitle;

    @Pattern(regexp = LENGTH, message = "A song must have a length expressed as _h _m _s!")
    @Column(name="song_length")
    private String songLength;

    @Column(name="language")
    private String language;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="id_album")
    private Album album;

}