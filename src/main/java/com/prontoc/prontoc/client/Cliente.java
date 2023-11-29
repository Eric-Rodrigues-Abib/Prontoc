package com.prontoc.prontoc.client;
import java.net.*;
import java.io.*;

public class Cliente
{
    public static final String HOST_PADRAO = "localhost";
    public static final int PORTA_PADRAO = 6969;

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
            servidor.receba(new TratadoraDeLogin(password));
            Comunicado comunicado = null;
            if(comunicado instanceof TratadoraDeLogin){
                RespostaSenha respostaSenha =(RespostaSenha)servidor.envie();
                if(respostaSenha.equals(true))
                {
                    System.out.println("Resultado da senha: "+respostaSenha.getResultado()+"\n");
                    return true;
                }
                return false;
            }
        }
        catch (Exception erro)
        {
            System.err.println ("Erro de comunicacao com o servidor;");
            System.err.println ("Tente novamente!");
            System.err.println ("Caso o erro persista, termine o programa");
            System.err.println ("e volte a tentar mais tarde!\n");
        }
        return false;
    }
}

