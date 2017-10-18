package com.example.admin.chat.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by admin on 05/10/17.
 */

public class Mensagem implements Serializable {
    @SerializedName("text")
    private String texto;
    private int id;

    public Mensagem(int id, String texto){
        this.id = id;
        this.texto = texto;
    }

    public String getTexto() {
        return this.texto;
    }

    public int getId() {
        return this.id;
    }
}
