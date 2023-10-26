package com.tabs.tabsapi.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class DataStructureDEDTO {
    private String city;
    private List<GenderStructureDEDTO> genders;
}
