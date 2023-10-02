package com.tabs.tabsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class Node {
    private Node next;
    private Kid data;

    public Node(Kid data) {
        this.data = data;
    }
}
