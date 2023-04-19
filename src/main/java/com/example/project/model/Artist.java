package com.example.project.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.example.project.model.Regex.CNP_REGEX;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
public class Artist extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTIST_ID")
    private Long id;

    @Pattern(regexp = CNP_REGEX, message = "Invalid CNP!")
    @NotBlank(message = "CNP must be provided!")
    private String cnp;

    @NotBlank(message = "You must enter the artist's Stage Name!")
    @Size(max = 400)
    @Column(name="stage_name")
    private String stageName;

    @ManyToOne
    @JoinColumn(name = "FK_RECORDLABEL_ID")
    @ToString.Exclude
    private RecordLabel recordLabel;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Consult> consults;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_ID")
    @ToString.Exclude
    private Address address;
}
