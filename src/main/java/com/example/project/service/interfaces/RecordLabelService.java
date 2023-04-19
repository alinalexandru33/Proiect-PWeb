package com.example.project.service.interfaces;

import com.example.project.model.RecordLabel;

import java.util.List;
import java.util.Optional;

public interface RecordLabelService {


    List<RecordLabel> getAllRecordLabels();

    RecordLabel getRecordLabelById(Long id);

    Boolean checkIfRecordLabelExists(Long id);

    Optional<RecordLabel> getRecordLabelByName(String recordLabelName);

    RecordLabel saveRecordLabel(RecordLabel recordLabel);

    void deleteRecordLabelById(Long id);
}
