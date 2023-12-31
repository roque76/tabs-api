package com.tabs.tabsapi.controller;

import com.tabs.tabsapi.controller.dto.ResponseDTO;
import com.tabs.tabsapi.exceptions.KidsException;
import com.tabs.tabsapi.model.Kid;
import com.tabs.tabsapi.service.ListSEService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @PostMapping(path="/addtofinal")
    public ResponseEntity<ResponseDTO> addToFinal(@Valid @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.addToEnd(kid),null),HttpStatus.OK);
    }

    @PostMapping(path="/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@Valid @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.addToStart(kid),null),HttpStatus.OK);
    }

    @PostMapping(path = "insertinpos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos,@Valid @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.insertInPos(pos,kid),null),HttpStatus.OK);
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

    @GetMapping(path="/intercalatebygender")
    public ResponseEntity<ResponseDTO> intercalateByGender(){
        String output = listSEService.intercalateByGender();
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

    @DeleteMapping(path = "/deletebyid/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id){
        String output = listSEService.deleteById(id);
        if(output.equals("Insertado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);
        }
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
    public ResponseEntity<ResponseDTO> updateInPos(@PathVariable byte pos,@Valid @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.updateInPos(pos,kid),null),HttpStatus.OK);
    }

    @GetMapping(path = "/cityreport")
    public ResponseEntity<ResponseDTO> cityReport(){
        Object output = null;
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listSEService.cityReport(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);

        }
    }

    @GetMapping(path="/test")
    public ResponseEntity<ResponseDTO> getCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listSEService.getCities(),null),HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidation(MethodArgumentNotValidException exception){
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).toList();

        return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                null,errors),HttpStatus.OK);
    }

}
