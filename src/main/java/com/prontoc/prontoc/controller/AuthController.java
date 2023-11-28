package com.prontoc.prontoc.controller;

import com.prontoc.prontoc.client.Cliente;
import com.prontoc.prontoc.service.AuthService;
import com.prontoc.prontoc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    //Este método recebe um objeto Usuario no corpo da solicitação
    // (representado pela anotação (@RequestBody) com os campos de email e senha.

    @PostMapping("/login") //endpoint
    public ResponseEntity<String> login(@RequestBody User user) {
        if (authService.autenticar(user.getEmail(), user.getPassword())) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Verifique seu email e senha.");
        }
    }

    @PostMapping("/signin") //endpoint
    public ResponseEntity<String> signin(@RequestBody User user){
        return ResponseEntity.ok("Criado com sucesso");
    }
}