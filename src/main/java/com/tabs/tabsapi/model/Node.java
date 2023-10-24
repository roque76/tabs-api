package com.tabs.tabsapi.model;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class Node {
    @Valid
    private Kid data;
    private Node next;

    public Node(Kid data) {
        this.data = data;
    }
}
