package com.example.project.model.dto;

import com.example.project.model.Deals;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SelectedDeals {

    private Deals deals;
    private boolean isPresent;
}
