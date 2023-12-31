package com.tabs.tabsapi.model;

import com.tabs.tabsapi.controller.dto.*;
import com.tabs.tabsapi.exceptions.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ListDE {
    private NodeDE head;
    private int size=0;



    public void addKidToFinal(Kid newkid){
        NodeDE newNode = new NodeDE(newkid);
        if(this.head==null){
            this.head =newNode;
        } else {
            NodeDE temp = this.head;
            while(temp.getNext()!=null){
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }
        this.size++;
        System.out.println("New size value: "+this.size);

    }

    public List<Kid> getAllKids() throws KidsException {
        if(this.head==null){
            throw new KidsException("Lista vacia");
        }
        else{
            List<Kid> kids = new ArrayList<>();
            NodeDE temp = this.head;
            while(temp!=null){
                kids.add(temp.getData());
                temp = temp.getNext();
            }
            return kids;
        }
    }

    public void addToStart(Kid newKid){
        if(this.head==null){
            this.head = new NodeDE(newKid);
        }
        else{
            NodeDE newHead = new NodeDE(newKid);
            newHead.setNext(this.head);
            newHead.setPrevious(null);
            this.head.setPrevious(newHead);
            this.head=newHead;
        }
        this.size++;

    }

    public void insertInPos(int pos, Kid kid) {
        if (pos == 1) {
            this.addToStart(kid);

        } else if (pos > this.size) {
            this.addKidToFinal(kid);

        } else if (pos <= this.size) {
            NodeDE temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDE newNode = new NodeDE(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }
    }
    public void invertList() {
        if (this.head != null) {
            ListDE listCopy = new ListDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCopy.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCopy.getHead();
        }
    }
    public void invertEdges() {
        if (this.head != null) {
            NodeDE temp = this.head;
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
            ListDE listCopy = new ListDE();
            NodeDE temp = this.head;
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
    public void deleteById(String id) throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getData().getId().equals(id)) {
            //Nueva cabeza
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.size--;
        }
        else{
            NodeDE temp = this.head;
            while(temp!=null) {
                if (temp.getData().getId().equals(id)) {
                    NodeDE previous = temp.getPrevious();
                    temp.setPrevious(null);
                    previous.setNext(temp.getNext());
                    if(previous.getNext()!=null){
                        previous.getNext().setPrevious(previous);
                        System.out.println(previous.getNext().getPrevious().getData());
                    }
                    break;
                }
                temp = temp.getNext();
            }
            this.size--;
        }
    }
    public void deleteInPos(int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int cont = 1;

            while (cont < pos - 1) {
                temp = temp.getNext();
                cont++;
            }

            temp.getNext().setPrevious(null);
            temp.setNext(temp.getNext().getNext());
        }

        this.size--;
    }
    public void deleteKamikaze(int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int cont = 1;

            while (cont!=pos) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE previous = temp.getPrevious();
            temp.setPrevious(null);
            previous.setNext(temp.getNext());
            if(previous.getNext()!=null){
                previous.getNext().setPrevious(previous);
                System.out.println(previous.getNext().getPrevious().getData());
            }

        }

        this.size--;
    }

    public List<String> getCities() {
        NodeDE temp = this.head;
        List<String> cities = new ArrayList<>();

        while (temp != null) {
            String city = temp.getData().getCity().getName();
            if (!cities.contains(city)) {
                cities.add(city);
            }
            temp = temp.getNext();
        }

        return cities;
    }

    public List<DataStructureDEDTO> cityReport() throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacía");
        } else {
            List<String>  cities = this.getCities();

            List<DataStructureDEDTO> finalReport = new ArrayList<>();

            for(String city :cities){
                int maleCount=0;
                int femaleCount =0;
                int oneToTenMale=0;
                int passTenMale=0;
                int oneToTenFemale=0;
                int passTenFemale=0;
                NodeDE temp = this.head;
                while(temp!=null){

                    if(temp.getData().getCity().getName().equals(city)){

                        if(temp.getData().getGender().equals("Male")&&
                                temp.getData().getAge()<10&&temp.getData().getBrothers()>0){
                            maleCount++;
                            oneToTenMale++;

                        }
                        if(temp.getData().getGender().equals("Male")&&
                                temp.getData().getAge()>=10&&temp.getData().getBrothers()>0){
                            maleCount++;
                            passTenMale++;

                        }
                        if(temp.getData().getGender().equals("Female")&&
                                temp.getData().getAge()<10&&temp.getData().getBrothers()>0){
                            femaleCount++;
                            oneToTenFemale++;

                        }
                        if(temp.getData().getGender().equals("Female")&&
                                temp.getData().getAge()>=10&&temp.getData().getBrothers()>0){
                            femaleCount++;
                            passTenFemale++;
                        }
                    }

                    temp = temp.getNext();
                }
                //Set lists for gender objects
                //Set male list
                List<BrothersStructureDEDTO> brothersMales = new ArrayList<>();
                brothersMales.add(new BrothersStructureDEDTO("One to ten males",oneToTenMale));
                brothersMales.add(new BrothersStructureDEDTO("Pass ten males",passTenMale));
                //Set female list
                List<BrothersStructureDEDTO> brothersFemales = new ArrayList<>();
                brothersFemales.add(new BrothersStructureDEDTO("One to ten females",oneToTenFemale));
                brothersFemales.add(new BrothersStructureDEDTO("Pass to ten females",passTenFemale));
                //Set genders list & objects
                List<GenderStructureDEDTO> genders =new ArrayList<>();
                genders.add(new GenderStructureDEDTO("Male",brothersMales,maleCount));
                genders.add(new GenderStructureDEDTO("Female",brothersFemales,femaleCount));
                // Objeto para final report
                finalReport.add(new DataStructureDEDTO(city,genders));

            }

            return finalReport;
        }
    }

}
