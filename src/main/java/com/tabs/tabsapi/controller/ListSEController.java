package com.tabs.tabsapi.controller;

import com.tabs.tabsapi.controller.dto.ResponseDTO;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listse")
public class ListSEController {

    @Autowired
    private ListSEService listSEService;

    @GetMapping(path="/getall")
    public ResponseEntity<ResponseDTO> getAll(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.getKids().getHead(),null), HttpStatus.OK);
    }

    @GetMapping(path="/invert")
    public ResponseEntity<ResponseDTO> invertList(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.invert(),null),HttpStatus.OK);
    }

    @GetMapping(path="/invertedges")
    public ResponseEntity<ResponseDTO> invertEdges(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.invertEdges(),null),HttpStatus.OK);
    }

    @DeleteMapping (path = "/deleteinpos/{pos}")
    public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
            String output = listSEService.deleteInPos(pos);

            if (output.equals("Fuera de rango")){
                List<String> errors = new ArrayList<>();
                errors.add(output);
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                        null,errors),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                        output,null),HttpStatus.OK);
            }

    }

    @PutMapping(path = "/updateinpos/{pos}")
    public ResponseEntity<ResponseDTO> updateInPos(@PathVariable byte pos, @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.updateInPos(pos,kid),null),HttpStatus.OK);
    }

    @PostMapping(path = "insertinpos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos,@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.insertInPos(pos,kid),null),HttpStatus.OK);
    }

}
