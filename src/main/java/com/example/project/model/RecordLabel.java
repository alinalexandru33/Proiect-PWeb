package com.example.project.model;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

import static com.example.project.model.Regex.NAME_REGEX;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recordLabel")
public class RecordLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_LABEL_ID")
    private Long id;

    @NotBlank(message = "Record Label name must be provided!")
    @Pattern(regexp = NAME_REGEX, message = "Record Label name is invalid!")
    private String name;

    @OneToMany(mappedBy = "recordLabel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Manager> managers;

    @OneToMany(mappedBy = "recordLabel", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Artist> artists;
}
