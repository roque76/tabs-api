package com.tabs.tabsapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class GenderStructureDEDTO {
    private String gender;
    private List<BrothersStructureDEDTO> quantity;
    private int total_quantity;
}
