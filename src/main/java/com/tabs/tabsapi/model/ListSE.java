package com.tabs.tabsapi.model;

import com.tabs.tabsapi.controller.dto.DataStructureDTO;
import com.tabs.tabsapi.controller.dto.GenderStructureDTO;
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
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
        } else {
            Node temp = this.head;
            int cont = 1;

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

        } else if (pos > this.size) {
            this.addKidToFinal(kid);

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

    public List<String> getCities() {
        Node temp = this.head;
        List<String> cities = new ArrayList<>();

        while (temp != null) {
            String city = temp.getData().getCity();
            if (!cities.contains(city)) {
                cities.add(city);
            }
            temp = temp.getNext();
        }

        return cities;
    }

    public List<DataStructureDTO> cityReport() throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacía");
        } else {
            List<String>  cities = this.getCities();

            List<DataStructureDTO> cities_report = new ArrayList<>();

            for(String city :cities){
                int total_city_count=0;
                int male_count=0;
                int female_count =0;
               Node temp = this.head;
               while(temp!=null){
                   if(temp.getData().getCity().equals(city)){
                       System.out.println("------");
                       System.out.println(temp.getData());
                       System.out.println("----");
                       System.out.println(city);
                       if(temp.getData().getGender().equals("Male")){
                           System.out.println("Male");
                           System.out.println("---");
                           System.out.println(temp.getData());
                           System.out.println("Previous value: "+male_count);
                           System.out.println("----");
                           male_count++;
                           System.out.println("New male value:  "+male_count);
                       }
                       if(temp.getData().getGender().equals("Female")){
                           System.out.println("Female");
                           System.out.println("-----");
                           System.out.println(temp.getData());
                           System.out.println("Previous value: "+female_count);
                           System.out.println("-----");
                           female_count++;
                           System.out.println("New Female value:  "+female_count);
                       }
                       total_city_count++;
                       System.out.println("New total count in"+city+"-----"+total_city_count);
                   }

                   temp = temp.getNext();
               }
                GenderStructureDTO city_females = new GenderStructureDTO("Female",female_count);
                GenderStructureDTO city_males = new GenderStructureDTO("Males",male_count);
                System.out.println(city+"Females in the city  "+ city_females);
                System.out.println(city+"Males in the city  "+city_males);

                List<GenderStructureDTO> genders = new ArrayList<>();
                genders.add(city_females);
                genders.add(city_males);

                DataStructureDTO finalCityReport = new DataStructureDTO(city,total_city_count,genders);
                System.out.println("----");
                System.out.println(finalCityReport);

                cities_report.add(finalCityReport);
            }

            System.out.println("---");
            System.out.println(cities_report);

            return cities_report;
        }
    }

}
