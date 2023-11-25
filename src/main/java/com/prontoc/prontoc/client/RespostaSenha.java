package com.prontoc.prontoc.client;

public class RespostaSenha extends Comunicado
{
    private String Resultado;

    public RespostaSenha (String Resultado)
    {
        this.Resultado = Resultado;
    }

    public String getResultado() {
        return Resultado;
    }

    public String toString()
    {
        return (" "+this.Resultado);
    }
}
