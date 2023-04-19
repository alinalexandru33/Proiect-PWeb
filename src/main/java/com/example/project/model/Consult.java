package com.example.project.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Consult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSULT_ID")
    private Long id;

    @NotNull(message = "Date must be provided!")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date date = new Date();


    @Length(min = 5, message = "Please enter at least 5 characters!")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "FK_MANAGER_ID")
    @ToString.Exclude
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "FK_ARTIST_ID")
    @ToString.Exclude
    private Artist artist;

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinTable(name = "agreement",
            joinColumns = @JoinColumn(name = "CONSULT_ID", referencedColumnName = "CONSULT_ID"),
            inverseJoinColumns = @JoinColumn(name = "DEAL_ID", referencedColumnName = "DEAL_ID"))
    private List<Deals> deals;
}
