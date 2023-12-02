package com.prontoc.prontoc.controller;

import com.prontoc.prontoc.model.Patient;
import com.prontoc.prontoc.model.User;
import com.prontoc.prontoc.service.PatientService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    //util para trabalhar com front-end para retornar códigos de status adequados
    @GetMapping("/allpatients")
    public ResponseEntity<List<Patient>> getAllPatients(){
        return new ResponseEntity<List<Patient>>(patientService.AllPatients(), HttpStatus.OK);
    }

    //estamos tentando pesquisar um paciente pelo seu ID
    //o pathvariable permite ao framework saber que passaremos
    //as informacoes que obtivemos no mapeamento como uma variável de caminho
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getSinglePatient(@PathVariable String id){
        return new ResponseEntity<Optional<Patient>>(patientService.singlePatient(id), HttpStatus.OK);
    }



}
