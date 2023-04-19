package com.example.project.service.interfaces;

import com.example.project.model.Consult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsultService {

    Page<Consult> getAllConsults(Pageable pageable);

    Page<Consult> getConsultsByManagerId(Pageable pageable, Long managerId);

    List<Consult> getConsultsByManagerId(Long managerId);

    Consult getConsultById(Long id);

    Consult saveConsult(Consult consult);

    boolean isMyConsult(Long consultId);

    void deleteConsultById(Long id);
}
