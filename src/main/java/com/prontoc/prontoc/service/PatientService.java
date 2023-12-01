

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

    public List<Patient> AllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> singlePatient(String id){
        return patientRepository.findById(id);
    }

    public Patient CreateNewPatient(Patient patient, User crm)
    {
        Optional<Patient> existingPatientOptional = patientRepository.findById(patient.getPatientid());

        if(existingPatientOptional.isPresent())
        {
            Patient existingPatient = existingPatientOptional.get();
            List<User> currentDoctor = existingPatient.getCrm();

            if(!currentDoctor.contains(crm))
            {
                currentDoctor.add(crm);
                existingPatient.setCrm(currentDoctor);
                return patientRepository.save(existingPatient);
            }
            else
            {
                return existingPatient;
            }
        }
        else
        {
            patient.setCrm(List.of(crm));
            return patientRepository.save(patient);
        }
    }
}
