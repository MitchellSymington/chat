package com.example.admin.chat.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.example.admin.chat.activity.MainActivity;
import com.example.admin.chat.event.MensagemEvent;
import com.example.admin.chat.modelo.Mensagem;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by admin on 07/10/17.
 */

public class OuvirMensagemCallback implements Callback<Mensagem> {

    private MainActivity activity;
    private EventBus bus;

    public OuvirMensagemCallback(EventBus bus, Context context) {
        this.activity = (MainActivity) context;
        this.bus = bus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {
        if(response.isSuccessful()) {
            Mensagem mensagem = response.body();

            bus.post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }
}
