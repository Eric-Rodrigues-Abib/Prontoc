package com.prontoc.prontoc.repository;

import com.prontoc.prontoc.model.Patient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, ObjectId> {

    //caso tenha um outro id sem ser o ja criado pelo mongo, alterar aqui
    //alterar tambem no controller e no service.
    Optional<Patient> findById(String id);

    List<Patient> findBycrm(String crm);

    Patient findByNameP(String name);
}
