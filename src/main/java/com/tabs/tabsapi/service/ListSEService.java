package com.tabs.tabsapi.service;

import com.tabs.tabsapi.controller.dto.DataStructureDTO;
import com.tabs.tabsapi.exceptions.KidsException;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.util.List;

@Data
@Service
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        // simular que lei un archivo o una base de datos
        kids = new ListSE();
        kids.addKidToFinal(new Kid("1005085752","Valeria Osorio"
                ,"Female", (byte) 20,"Cartago"));
        kids.addKidToFinal(new Kid("356373763","Jhair Torres"
                ,"Male", (byte) 18,"Mocoa"));
        kids.addKidToFinal(new Kid("4554544554","Sergio Núñez"
                ,"Male", (byte) 19,"Pitalito"));
        kids.addKidToFinal(new Kid("1056122169","John Jaime Madrid",
                "Male",(byte)18,"Manizales"));

    }
    public String invert(){
        kids.invertList();
        return "Lista invertida";
    }

    public String invertEdges(){
        kids.invertEdges();
        return "Invertidos";
    }

    public String updateInPos(byte pos, Kid kid){
        kids.updateInPos(pos,kid);
        return "Actualizado";
    }

    public String deleteInPos(int pos){
        try {
            kids.deleteInPos(pos);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String insertInPos(int pos, Kid kid){
        kids.insertInPos(pos,kid);
        return "Adicionado";
    }

    public String addToEnd(Kid kid){
        kids.addKidToFinal(kid);
        return "Adicionado";
    }

    public String addToStart(Kid kid){
        kids.addToStart(kid);
        return "Adicionado";
    }

    public String deleteById(String id){
        try {
            kids.deleteById(id);
            return "Insertado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String intercalateByGender(){
        try{
            kids.intercalateByGender();
            return "Intercalado";
        }catch (KidsException e){
            return e.getMessage();
        }
    }

    public List<DataStructureDTO> cityReport() throws KidsException {
        try {
            return kids.cityReport();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public List<String> getCities(){
        return kids.getCities();
    }

}
