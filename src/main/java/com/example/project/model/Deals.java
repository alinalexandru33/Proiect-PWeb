package com.example.project.model;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Deals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEAL_ID")
    private Long id;

    @NotNull(message = "Signing date must be provided!")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @Column(name="signing_date")
    private Date signingDate;


    @NotNull(message = "Contract length must be provided!")
    @Min(value = 1, message = "Contract length must be positive!")
    @Column(name="contract_length")
    private Integer contractLength;


    @ManyToMany
    @JoinTable(name = "agreement",
            joinColumns = @JoinColumn(name = "DEAL_ID", referencedColumnName = "DEAL_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONSULT_ID", referencedColumnName = "CONSULT_ID"))
    @ToString.Exclude
    private List<Consult> consults;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deals that = (Deals) o;
        return id.equals(that.id) && signingDate.equals(that.signingDate) && contractLength.equals(that.contractLength) && consults.equals(that.consults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, signingDate, contractLength, consults);
    }
}
