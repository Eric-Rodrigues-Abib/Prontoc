package com.prontoc.prontoc.Comunicados;

import com.prontoc.prontoc.Comunicados.Comunicado;

public class Resultado extends Comunicado
{
    private Boolean resultado;

    public Resultado(Boolean Resultado)
    {
        this.resultado = Resultado;
    }

    public Boolean getResultado() {
        return resultado;
    }

    public String toString()
    {
        return (" "+this.resultado);
    }
}
