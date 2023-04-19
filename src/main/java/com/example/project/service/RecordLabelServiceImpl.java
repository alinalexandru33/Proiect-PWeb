package com.example.project.service;

import com.example.project.exception.CustomException;
import com.example.project.exception.EntityNotFoundException;
import com.example.project.model.RecordLabel;
import com.example.project.repository.RecordLabelRepository;
import com.example.project.service.interfaces.RecordLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordLabelServiceImpl implements RecordLabelService {

    private final RecordLabelRepository recordLabelRepository;

    @Autowired
    public RecordLabelServiceImpl(RecordLabelRepository recordLabelRepository) {
        this.recordLabelRepository = recordLabelRepository;
    }

    public List<RecordLabel> getAllRecordLabels() {
        return recordLabelRepository.findAll();
    }

    public RecordLabel getRecordLabelById(Long id) {
        return recordLabelRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException.builder()
                        .entityId(id)
                        .entityType("RecordLabel")
                        .build()
                );
    }

    public Boolean checkIfRecordLabelExists(Long id) {
        return recordLabelRepository.findById(id).isPresent();
    }

    public Optional<RecordLabel> getRecordLabelByName(String recordLabelName) {
        return recordLabelRepository.findRecordLabelByName(recordLabelName);
    }

    public RecordLabel saveRecordLabel(RecordLabel recordLabel) {

        Optional<RecordLabel> recordLabelByName = getRecordLabelByName(recordLabel.getName());
        if (recordLabelByName.isPresent()) {
            throw new CustomException(String.format("RecordLabel with name %s already exists!", recordLabel.getName()));
        }

        return recordLabelRepository.save(recordLabel);
    }

    public void deleteRecordLabelById(Long id) {
        recordLabelRepository.deleteById(id);
    }
}
