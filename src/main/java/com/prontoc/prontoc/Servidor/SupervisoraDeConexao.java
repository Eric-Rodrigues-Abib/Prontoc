package com.prontoc.prontoc.Servidor;

import com.prontoc.prontoc.Comunicados.Comunicado;
import com.prontoc.prontoc.Comunicados.Resultado;
import com.prontoc.prontoc.Comunicados.TratamentoDeSenha;
import java.io.*;
import java.net.*;
import java.util.*;
public class SupervisoraDeConexao extends Thread{
    private String              password;
    private Parceiro            usuario;
    private Socket              conexao;
    private ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao
            (Socket conexao, ArrayList<Parceiro> usuarios)
            throws Exception
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (usuarios==null)
            throw new Exception ("Usuarios ausentes");

        this.conexao  = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {

        ObjectOutputStream transmissor;
        try
        {
            transmissor =
                    new ObjectOutputStream(
                            this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            return;
        }

        ObjectInputStream receptor=null;
        try
        {
            receptor=
                    new ObjectInputStream(
                            this.conexao.getInputStream());
        }
        catch (Exception err0)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception falha)
            {} // so tentando fechar antes de acabar a thread

            return;
        }

        try
        {
            this.usuario =
                    new Parceiro (this.conexao,
                            receptor,
                            transmissor);
        }
        catch (Exception erro)
        {} // sei que passei os parametros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add(this.usuario);
            }
            Comunicado comunicado = this.usuario.envie();
            if(comunicado==null)
                return;
            else if(comunicado instanceof TratamentoDeSenha)
            {
                TratamentoDeSenha tratamentoDeSenha = (TratamentoDeSenha)comunicado;
                this.password = tratamentoDeSenha.getPassword();
                if(this.password != null)
                {
                    if (this.password.length() >= 8)
                    {
                        this.usuario.receba(new Resultado(true));
                        synchronized (this.usuarios)
                        {
                            this.usuarios.remove(this.usuario);
                        }
                        this.usuario.adeus();
                        return;
                    }
                    this.usuario.receba(new Resultado(false));
                    synchronized (this.usuarios)
                    {
                        this.usuarios.remove(this.usuario);
                    }
                    this.usuario.adeus();
                    return;
                }
                this.usuario.receba(new Resultado(false));
                synchronized (this.usuarios)
                {
                    this.usuarios.remove(this.usuario);
                }
                this.usuario.adeus();

            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close();
                receptor.close();
            }
            catch (Exception falha)
            {}
            return;
        }
    }
}