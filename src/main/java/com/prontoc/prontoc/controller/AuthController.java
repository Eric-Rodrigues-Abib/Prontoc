package com.prontoc.prontoc.controller;

import com.prontoc.prontoc.model.AuthService;
import com.prontoc.prontoc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    //Este método recebe um objeto Usuario no corpo da solicitação
    // (representado pela anotação (@RequestBody) com os campos de email e senha.

    @PostMapping("/login") //endpoint
    public String login(@RequestBody User usuario) {
        if (authService.autenticar(usuario.getEmail(), usuario.getSenha())) {
            return "Login realizado com sucesso!";
        } else {
            return "Credenciais inválidas. Verifique seu email e senha.";
        }
    }
}