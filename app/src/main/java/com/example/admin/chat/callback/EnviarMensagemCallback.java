package com.example.admin.chat.callback;

import com.example.admin.chat.modelo.Mensagem;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by admin on 07/10/17.
 */

public class EnviarMensagemCallback implements retrofit2.Callback<Void> {


    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {

    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
