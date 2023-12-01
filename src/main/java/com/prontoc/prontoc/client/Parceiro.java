package com.prontoc.prontoc.client;
import com.prontoc.prontoc.Comunicados.Comunicado;

import java.io.*;
import java.net.*;
import java.util.concurrent.Semaphore;

public class Parceiro
{
    private Socket             conexao;
    private ObjectInputStream  receptor;
    private ObjectOutputStream transmissor;

    private Comunicado proximoComunicado=null;

    private Semaphore mutEx = new Semaphore (1,true);

    public Parceiro (Socket             conexao,
                     ObjectInputStream  receptor,
                     ObjectOutputStream transmissor)
            throws Exception // se parametro nulos
    {
        if (conexao==null)
            throw new Exception ("Conexao ausente");

        if (receptor==null)
            throw new Exception ("Receptor ausente");

        if (transmissor==null)
            throw new Exception ("Transmissor ausente");

        this.conexao     = conexao;
        this.receptor    = receptor;
        this.transmissor = transmissor;
    }

    public void receba (Comunicado x) throws Exception
    {
        try
        {
            this.transmissor.writeObject (x);
            this.transmissor.flush       ();
        }
        catch (IOException erro)
        {
            throw new Exception ("Erro de transmissao");
        }
    }

    public Comunicado espie () throws Exception
    {
        Comunicado ret = null;
        System.out.println("entrando no espie");
        try
        {
            this.mutEx.acquireUninterruptibly();
            ret = this.proximoComunicado;
            this.mutEx.release();
            if (ret==null) {
                ret = (Comunicado) this.receptor.readObject();
                this.mutEx.acquireUninterruptibly();
                this.proximoComunicado = ret;
                this.mutEx.release();
            }
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
        System.out.println("retornando do espie:" + ret);
        return ret;
    }

    public Comunicado envie () throws Exception
    {
        Comunicado ret = null;
        try
        {
            this.mutEx.acquireUninterruptibly();
            ret = this.proximoComunicado;
            this.mutEx.release();
            if (ret==null) {
                ret = (Comunicado) this.receptor.readObject();
                this.mutEx.acquireUninterruptibly();
                this.proximoComunicado = ret;
                this.mutEx.release();
            }


        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de recepcao");
        }
        return ret;
    }

    public void adeus () throws Exception
    {
        try
        {
            this.transmissor.close();
            this.receptor   .close();
            this.conexao    .close();
        }
        catch (Exception erro)
        {
            throw new Exception ("Erro de desconexao");
        }
    }
}