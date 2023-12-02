package com.prontoc.prontoc.service;

import com.prontoc.prontoc.model.Patient;
import com.prontoc.prontoc.repository.PatientRepository;
import com.prontoc.prontoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;


    public List<Patient> getPacientesDoMedico (String crm)
    {
        return patientRepository.findBycrm(crm);
    }

    public void atualizarPaciente(Patient patient)
    {
        patientRepository.save(patient);
    }

    public void salvarNovoPaciente(Patient patient)
    {
        patientRepository.save(patient);
    }
}
