package com.prontoc.prontoc.client;
import java.net.*;
import java.io.*;

public class Cliente
{
    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 6969;

    public Cliente (String senha)
    {
        Socket conexao = null;
        try
        {
            conexao = new Socket(Cliente.HOST_PADRAO, Cliente.PORTA_PADRAO);
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectOutputStream transmissor = null;
        try
        {
            transmissor = new ObjectOutputStream(conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        ObjectInputStream receptor = null;
        try
        {
            receptor = new ObjectInputStream(conexao.getInputStream());
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        Parceiro servidor = null;
        try
        {
            servidor = new Parceiro(conexao, receptor, transmissor);
        }
        catch (Exception erro)
        {
            System.err.println("Indique o servidor e a porta corretos!\n");
            return;
        }

        TratadoraDeComunicadoDeDesligamento tratadoraDeComunicadoDeDesligamento = null;
        try
        {
            tratadoraDeComunicadoDeDesligamento = new TratadoraDeComunicadoDeDesligamento(servidor);
        }
        catch (Exception erro)
        {}

        tratadoraDeComunicadoDeDesligamento.start();

        //a partir daqui faremos a parte de receber e enviar ao servidor
        try
        {
            servidor.receba(new TratadoraDeLogin(senha));

            Comunicado comunicado = null;
            if(comunicado instanceof RespostaSenha)
            {
                RespostaSenha respostaSenha =(RespostaSenha)servidor.envie();
                System.out.println("Resultado da senha: "+respostaSenha.getResultado()+"\n");
            }
        }
        catch (Exception erro)
        {
            System.err.println ("Erro de comunicacao com o servidor;");
            System.err.println ("Tente novamente!");
            System.err.println ("Caso o erro persista, termine o programa");
            System.err.println ("e volte a tentar mais tarde!\n");
        }

//        try
//        {
//            servidor.receba(new TratadoraDeLogin(senha));
//        }
//        catch (Exception erro)
//        {}

//        try
//        {
//            Comunicado comunicado = null;
//            do
//            {
//                comunicado =(Comunicado)servidor.espie();
//            }
//            while (!(comunicado instanceof RespostaSenha));
//            RespostaSenha respostaSenha = (RespostaSenha)servidor.envie();
//            System.out.println("Resultado da senha: "+respostaSenha.getResultado());
//        }
//        catch (Exception erro)
//        {
//            System.err.println ("Erro de comunicacao com o servidor;");
//            System.err.println ("Tente novamente!");
//            System.err.println ("Caso o erro persista, termine o programa");
//            System.err.println ("e volte a tentar mais tarde!\n");
//        }
    }
}

