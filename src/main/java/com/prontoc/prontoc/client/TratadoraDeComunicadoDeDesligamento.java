package com.prontoc.prontoc.client;
import com.prontoc.prontoc.Comunicados.ComunicadoDeDesligamento;

public class TratadoraDeComunicadoDeDesligamento extends Thread
{
    private Parceiro servidor;

    public TratadoraDeComunicadoDeDesligamento (Parceiro servidor) throws Exception
    {
        if (servidor==null)
            throw new Exception ("Porta invalida");

        this.servidor = servidor;
    }

    public void run ()
    {
        for(;;)
        {
            try
            {
                this.servidor.envie();
                System.out.println ("\nO servidor vai ser desligado agora;");
                System.err.println ("volte mais tarde!\n");
                System.exit(0);

            }
            catch (Exception erro)
            {}
        }
    }
}