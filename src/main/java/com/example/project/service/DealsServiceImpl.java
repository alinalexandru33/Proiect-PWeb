package com.example.project.service;

import com.example.project.exception.CustomException;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.Deals;
import com.example.project.repository.DealsRepository;
import com.example.project.service.interfaces.DealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DealsServiceImpl implements DealsService {

    private final DealsRepository dealsRepository;

    public List<Deals> getAllDeals() {
        return dealsRepository.findAll().stream()
                .sorted(Comparator.comparing(Deals::getSigningDate).thenComparing(Deals::getContractLength))
                .collect(Collectors.toList());
    }

    public Deals getDealsById(Long id) {
        return dealsRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(id)
                        .entityType("Deals")
                        .build()
                );
    }

    public Boolean checkIfDealsExists(Long id) {
        return dealsRepository.findById(id).isPresent();
    }

    public Deals saveDeals(Deals deals) {
        checkIfDealsAlreadyExistsByDateAndContract(deals.getSigningDate(), deals.getContractLength());
        return dealsRepository.save(deals);
    }

    public List<Deals> findDealsByIdContains(List<Long> ids) {
        return dealsRepository.findByIdIsIn(ids);
    }

    public void deleteDealsById(Long id) {
        dealsRepository.deleteById(id);
    }

    private void checkIfDealsAlreadyExistsByDateAndContract(Date date, Integer contractLength) {
        if (dealsRepository.findDealsBySigningDateAndContractLength(date, contractLength).isPresent()) {
            throw new CustomException(String.format("Deal %s with contract length %s already exists!", date, contractLength));
        }
    }
}
