package com.tabs.tabsapi.model;

import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;


    public void addKidToFinal(Kid newkid) {
        Node new_node = new Node( newkid);
        if (this.head == null) {
            this.head = new_node;
        } else {
            Node tempNode = this.head;
            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
            }
            tempNode.setNext(new_node);
            size++;
        }
    }

    public void addToStart(Kid newKid){
        if(this.head ==null){
            //No hay datos
            this.head = new Node(newKid);
        }
        else{
            Node newNode = new Node(newKid);
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size ++;
    }
}
