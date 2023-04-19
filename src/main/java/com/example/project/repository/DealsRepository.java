package com.example.project.repository;

import com.example.project.model.Deals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DealsRepository extends JpaRepository<Deals, Long> {

    Optional<Deals> findDealsBySigningDateAndContractLength(Date SigningDate, Integer contractLength);

    List<Deals> findByIdIsIn(List<Long> ids);
}
