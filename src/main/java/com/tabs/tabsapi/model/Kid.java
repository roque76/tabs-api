package com.tabs.tabsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Kid {
    private String id;
    private String name;
    private String gender;
    private byte age;
    private String city;
}
