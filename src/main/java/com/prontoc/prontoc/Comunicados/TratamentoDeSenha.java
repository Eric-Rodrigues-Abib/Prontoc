package com.prontoc.prontoc.Comunicados;

import com.prontoc.prontoc.Comunicados.Comunicado;

public class TratamentoDeSenha extends Comunicado {
    private String password;

    public TratamentoDeSenha(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return this.password;
    }

    public String toString()
    {
        return (" "+this.password);
    }

}
