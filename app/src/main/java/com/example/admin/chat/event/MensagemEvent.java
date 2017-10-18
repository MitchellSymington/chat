package com.example.admin.chat.event;

import com.example.admin.chat.modelo.Mensagem;

/**
 * Created by admin on 17/10/17.
 */

public class MensagemEvent {

    public Mensagem mensagem;

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
