

package com.prontoc.prontoc.service;

import com.prontoc.prontoc.model.Patient;
import com.prontoc.prontoc.model.User;
import com.prontoc.prontoc.repository.PatientRepository;
import com.prontoc.prontoc.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    //isso fará com que o framework saiba que queremos que o framework
    //instacie essa classe aqui para nós
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> singlePatient(String id){
        return patientRepository.findById(id);
    }

    public Patient buscarPacientePorNome(String nameP)
    {
        return patientRepository.findByNameP(nameP);
    }

}
