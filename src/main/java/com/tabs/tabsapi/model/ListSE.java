package com.tabs.tabsapi.model;

import com.tabs.tabsapi.exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListSE {
    private Node head;
    private int size;


    public void addKidToFinal(Kid newkid) {
        Node new_node = new Node(newkid);
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

    public void addToStart(Kid newKid) {
        if (this.head == null) {
            //No hay datos
            this.head = new Node(newKid);
        } else {
            Node newNode = new Node(newKid);
            newNode.setNext(this.head);
            this.head = newNode;
        }
        this.size++;
    }

    public void invertList() {
        if (this.head != null) {
            ListSE listCopy = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCopy.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCopy.getHead();
        }
    }

    public void invertEdges() {
        if (this.head != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Kid lastKid = temp.getData();
            temp.setData(this.head.getData());
            this.head.setData(lastKid);
        }
    }

    public void intercalateByGender() throws KidsException{
        if(this.head == null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getNext()==null) {
            throw new KidsException("Insuficientes elementos");
        }
        else{
            ListSE listCopy = new ListSE();
            Node temp = this.head;
            int posMale = 1;
            int posFemale = 2;
            while(temp!= null){
                if(temp.getData().getGender().equals("Male")){
                    listCopy.insertInPos(posMale,temp.getData());
                    posMale = posMale+2;
                } else if (temp.getData().getGender().equals("Female")) {
                    listCopy.insertInPos(posFemale,temp.getData());
                    posFemale=posFemale+2;
                }
                temp = temp.getNext();
            }
            this.head= listCopy.getHead();
        }
    }

    public void updateInPos(int pos, Kid kid) {
        if (this.head != null) {
            Node temp = this.head;

            byte currentPos = 1;
            if (pos > this.size) {
                this.addKidToFinal(kid);
            }
            while (temp.getNext() != null) {
                if (currentPos == pos) {
                    temp.setData(kid);
                }
                temp = temp.getNext();
                currentPos++;
            }
        }
    }

    public void deleteInPos(int pos) throws KidsException {
        if (pos < 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 0) {
            this.head = this.head.getNext();
        } else {
            Node temp = this.head;
            int cont = 0;

            while (cont < pos - 1) {
                temp = temp.getNext();
                cont++;
            }

            temp.setNext(temp.getNext().getNext());
        }

        this.size--;
    }


    public void insertInPos(int pos, Kid kid) {
        if (pos == 1) {
            this.addToStart(kid);
            this.size++;

        } else if (pos > this.size) {
            this.addKidToFinal(kid);
            this.size++;

        } else if (pos<=this.size) {
            Node temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            Node newNode = new Node(kid);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            this.size++;

        }
    }

    public void deleteById(String id) throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getData().getId().equals(id)) {
            //Nueva cabeza
            this.head = this.head.getNext();
            this.size--;
        }
        else{
            Node temp = this.head;
            while(temp!=null) {
                if (temp.getNext().getData().getId().equals(id)) {
                    temp.setNext(temp.getNext().getNext());
                }
                temp = temp.getNext();
            }
            this.size--;
        }
    }

}
