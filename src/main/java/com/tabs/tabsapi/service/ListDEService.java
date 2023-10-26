package com.tabs.tabsapi.service;

import com.tabs.tabsapi.exceptions.KidsException;
import com.tabs.tabsapi.model.City;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.model.ListDE;
import com.tabs.tabsapi.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListDEService {
    private ListDE kids;

    public ListDEService() {
        // simular que lei un archivo o una base de datos
        kids = new ListDE();
        kids.addKidToFinal(new Kid("1005085752","Valeria Osorio"
                ,"Female", (byte) 20,new City("Cartago","52694")));
        kids.addKidToFinal(new Kid("356373763","Jhair Torres"
                ,"Male", (byte) 18,new City("Mocoa","86001")));
        kids.addKidToFinal(new Kid("4554544554","Sergio Núñez"
                ,"Male", (byte) 19,new City("Pitalito","41551")));
        kids.addKidToFinal(new Kid("1056122169","John Jaime Madrid",
                "Male",(byte)18,new City("Manizales","17001")));

    }

    public List<Kid> getall()throws KidsException {
        return kids.getAllKids();
    }

    public String addKidToEnd(Kid newKid){
        kids.addKidToFinal(newKid);
        return "Adicionado";
    }

    public String addKidToStart(Kid newKid){
        kids.addToStart(newKid);
        return "Adicionado";
    }

    public String insertInPos(int pos, Kid kid){
        kids.insertInPos(pos,kid);
        return  "Adicionado en: "+ pos;
    }
    public String invertList(){
        kids.invertList();
        return "Invertida";
    }
    public String invertEdges(){
        kids.invertEdges();
        return "Invertidos";
    }
    public String intercalateByGender(){
        try {
            kids.intercalateByGender();
            return "Intercalados";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }
    public String deleteById(String id){
        try {
            kids.deleteById(id);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }
    public String deleteInPos(int pos){
        try {
            kids.deleteInPos(pos);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }
    public String deleteKamikaze(int pos){
        try {
            kids.deleteKamikaze(pos);
            return "Kamikazeeeee";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }
}
