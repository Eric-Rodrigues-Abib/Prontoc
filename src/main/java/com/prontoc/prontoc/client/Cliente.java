package com.prontoc.prontoc.client;
import com.prontoc.prontoc.Comunicados.Comunicado;
import com.prontoc.prontoc.Comunicados.Resultado;
import com.prontoc.prontoc.Comunicados.TratamentoDeSenha;

import java.net.*;
import java.io.*;

public class Cliente
{
    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 5500;

    public boolean isValida(String password)
    {
        Socket conexao = null;
        try
        {
            conexao = new Socket(Cliente.HOST_PADRAO, Cliente.PORTA_PADRAO);
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return false;
        }

        ObjectOutputStream transmissor = null;
        try
        {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return false;
        }

        ObjectInputStream receptor = null;
        try
        {
            receptor = new ObjectInputStream(conexao.getInputStream());
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return false;
        }

        Parceiro servidor = null;
        try
        {
            servidor = new Parceiro(conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return false;
        }

//        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
//        try
//        {
//            tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor);
//        }
//        catch (Exception erro)
//        {}
//
//        tratadoraDeComunicadoDeDesligamento.start();

        //a partir daqui faremos a parte de receber e enviar ao servidor
        System.out.println("Bem Vindo ao servidor!");
        try
        {
            System.out.println("Enviando ao servidor: " + password);
            servidor.receba(new TratamentoDeSenha(password));
            System.out.println("Senha enviado ao servidor: " + password);
            Comunicado comunicado=null;
            System.out.println(comunicado);
            System.out.println("Esperando resposta do servidor....");

            comunicado = (Comunicado) servidor.envie();

            Resultado resultado = (Resultado) servidor.envie();
            System.out.println("Resposta recebida do servidor: " + resultado.getResultado());
            if(resultado.getResultado())
            {
                return true;
            }

            return false;


        } catch (Exception erro)
        {
            System.out.println(erro);
            System.err.println("Erro de comunicacao com o servidor;");
            System.err.println("Tente novamente!");
            System.err.println("Caso o erro persista, termine o programa");
            System.err.println("e volte a tentar mais tarde!\n");
        }
        return false;
    }
}

