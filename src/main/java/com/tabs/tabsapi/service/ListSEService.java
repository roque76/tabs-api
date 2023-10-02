package com.tabs.tabsapi.service;

import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.model.ListSE;
import lombok.Data;
import org.springframework.stereotype.Service;

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
}
