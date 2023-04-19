package com.example.project.repository;

import com.example.project.model.RecordLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordLabelRepository extends JpaRepository<RecordLabel, Long> {

    Optional<RecordLabel> findRecordLabelByName(String name);
}
