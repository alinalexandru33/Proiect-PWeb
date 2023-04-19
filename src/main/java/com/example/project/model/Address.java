package com.example.project.model;


import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.example.project.model.Regex.CITY_REGEX;
import static com.example.project.model.Regex.STREET_REGEX;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Length(min = 3, message = "Street name should have minimum 3 letters!")
    @Length(max = 30, message = "Street name should have maximum 30 letters!")
    @Pattern(regexp = STREET_REGEX, message = "Invalid street name")
    @NotBlank(message = "Street name must be provided!")
    private String street;

    @NotBlank(message = "Number should be provided!")
    @Min(value = 1, message = "Number should pe positive!")
    @Column(name = "NUMBER")
    private Integer no;

    @Length(min = 3, message = "City name should have minimum 3 letters!")
    @Length(max = 30, message = "City name should have maximum 30 letters!")
    @Pattern(regexp = CITY_REGEX, message = "Invalid city name")
    @NotBlank(message = "City name must be provided!")
    private String city;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Artist artist;
}
