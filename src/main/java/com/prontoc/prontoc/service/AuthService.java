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

    public boolean autenticar(String email, String password) {
        User usuario = usuarioRepository.findByEmail(email);
        System.out.println(usuario);
        if(usuario != null && password.equals(usuario.getPassword()))
        {
            return true;
        }else {
            return false;
        }
    }

    public void createnewuser(String name, String email, String password, String crm){
        User usuario = new User();
        usuario.setName(name);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setCrm(crm);

        usuarioRepository.save(usuario);
    }
}