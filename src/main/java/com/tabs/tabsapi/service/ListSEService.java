package com.tabs.tabsapi.service;

import com.tabs.tabsapi.exceptions.KidsException;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;

@Data
@Service
public class ListSEService {
    private ListSE kids;

    public ListSEService() {
        // simular que lei un archivo o una base de datos
        kids = new ListSE();
        kids.addKidToFinal(new Kid("1005085752","Valeria Osorio"
                ,"Female", (byte) 20));
        kids.addKidToFinal(new Kid("356373763","Jhair Torres"
                ,"Male", (byte) 18));
        kids.addKidToFinal(new Kid("4554544554","Sergio Núñez"
                ,"Male", (byte) 19));

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



}
