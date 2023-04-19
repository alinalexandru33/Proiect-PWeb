package com.example.project.repository;

import com.example.project.model.Consult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    List<Consult> getConsultsByManagerId(Long managerId);

    Page<Consult> getConsultByManagerId(Long managerId, Pageable page);
}
