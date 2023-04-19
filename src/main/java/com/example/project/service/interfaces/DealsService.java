package com.example.project.service.interfaces;

import com.example.project.model.Deals;

import java.util.List;

public interface DealsService {

    List<Deals> getAllDeals();

    Deals getDealsById(Long id);

    Boolean checkIfDealsExists(Long id);

    Deals saveDeals(Deals deals);

    List<Deals> findDealsByIdContains(List<Long> ids);

    void deleteDealsById(Long id);
}
