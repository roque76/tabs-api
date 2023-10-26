package com.tabs.tabsapi.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kid {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String gender;
    @Max(18)
    private byte age;
    @Valid
    private City city;
    private int brothers;
}
