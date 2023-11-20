package com.prontoc.prontoc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
public class PatientController {

    //util para trabalhar com front-end para retornar códigos de status adequados
    @GetMapping
    public ResponseEntity<String> allPatients(){
        return new ResponseEntity<>("All Patients!", HttpStatus.OK);
    }
}
