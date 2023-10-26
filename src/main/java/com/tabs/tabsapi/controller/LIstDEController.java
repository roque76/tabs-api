package com.tabs.tabsapi.controller;

import com.tabs.tabsapi.controller.dto.ResponseDTO;
import com.tabs.tabsapi.exceptions.KidsException;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.service.ListDEService;
import com.tabs.tabsapi.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listde")
public class LIstDEController {

    @Autowired
    private ListDEService listDEService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listDEService.getall(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                   null,errors),HttpStatus.OK);
        }
    }
    @PostMapping (path = "/addtofinal")
    public ResponseEntity<ResponseDTO> addToFinal(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.addKidToEnd(kid),null),HttpStatus.OK);
    }
    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.addKidToStart(kid),null),HttpStatus.OK);
    }
    @PostMapping(path="/insertinpos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos, @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.insertInPos(pos,kid),null),HttpStatus.OK);
    }
    @GetMapping(path = "/invertlist")
    public ResponseEntity<ResponseDTO> invertList(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.invertList(),null),HttpStatus.OK);
    }
    @GetMapping(path = "/invertedges")
    public ResponseEntity<ResponseDTO> invertEdges(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listDEService.invertEdges(),null),HttpStatus.OK);
    }
    @GetMapping(path="/intercalatebygender")
    public ResponseEntity<ResponseDTO> intercalateByGender(){
        String output = listDEService.intercalateByGender();
        if(output.equals("Lista vacia")||output.equals("Insuficientes elementos")){
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }
    @DeleteMapping (path="/deletebyid/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id){
        String output = listDEService.deleteById(id);
        if(output.equals("Eliminado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deletebypos/{pos}")
    public ResponseEntity<ResponseDTO> deleteByPos(@PathVariable int pos){
        String output = listDEService.deleteInPos(pos);
        if(output.equals("Eliminado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }

    }
    @DeleteMapping(path="/deletekamikaze/{pos}")
    public ResponseEntity<ResponseDTO> deleteKamikaze(@PathVariable int pos){
        String output = listDEService.deleteKamikaze(pos);
        if(output.equals("Kamikazeeeee")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
}
