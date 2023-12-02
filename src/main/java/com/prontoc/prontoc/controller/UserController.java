package com.prontoc.prontoc.controller;

import com.prontoc.prontoc.model.Patient;
import com.prontoc.prontoc.model.User;
import com.prontoc.prontoc.service.PatientService;
import com.prontoc.prontoc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.awt.*;

import static org.springframework.web.servlet.function.ServerResponse.noContent;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/{crm}/pacientes")
    public ResponseEntity<?> getPacienteDoMédico(@PathVariable String crm)
    {
        List<Patient> pacientes = userService.getPacientesDoMedico(crm);

        if(pacientes.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pacientes);

    }

    @PostMapping("/{crm}/addPatient")
    public ResponseEntity<?> addNewPatient(@PathVariable String crm, @RequestBody Patient patient, @RequestBody User user)
    {
        Patient pacienteexistente = patientService.buscarPacientePorNome(patient.getNameP());

        if(pacienteexistente != null)
        {
            pacienteexistente.addCrmMedicosrelacinados(crm);
            userService.atualizarPaciente(patient);
            return ResponseEntity.ok("paciente existente. Adicionando CRM do medico ao paciente");
        }
        else
        {
            patient.addCrmMedicosrelacinados(crm);
            userService.salvarNovoPaciente(patient);
            return ResponseEntity.ok("Novo paciente adicionado");
        }
    }
}
