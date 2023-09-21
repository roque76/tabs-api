package com.tabs.tabsapi.model;

public class ListSE {
    private Node head;
    private int size;


    public void addKidToFinal(Kid newkid) {
        Node new_node = new Node(null, newkid);
        if (head == null) {
            head = new_node;
        } else {
            Node tempNode = head;
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(new_node);
            size++;
        }
    }

    public void addKidToStart(Kid newkid){
        Node new_node = new Node(null,newkid);
        if (head==null){
            head = new_node;
        }
        else{
            Node tempNode = head;
            tempNode.setNext(new_node);
            size++;
        }
    }
}
