package com.tabs.tabsapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private Node next;
    private Object data;
}
