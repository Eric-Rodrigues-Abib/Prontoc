package com.prontoc.prontoc.service;

import com.prontoc.prontoc.model.User;
import com.prontoc.prontoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//escrevemos os métodos de acesso ao banco de dados
@Service
public class AuthService {

    @Autowired
    private UserRepository usuarioRepository;

    public boolean autenticar(String email, String senha) {
        User usuario = usuarioRepository.findByEmail(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}