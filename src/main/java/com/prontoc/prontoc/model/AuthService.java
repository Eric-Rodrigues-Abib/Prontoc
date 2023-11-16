package com.prontoc.prontoc.model;

import com.prontoc.prontoc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository usuarioRepository;

    public boolean autenticar(String email, String senha) {
        User usuario = usuarioRepository.findByEmail(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}