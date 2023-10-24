package com.tabs.tabsapi.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class City {
    @NotEmpty
    private String name;
    @NotEmpty
    private String code;
}
