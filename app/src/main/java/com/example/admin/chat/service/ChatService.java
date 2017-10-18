package com.example.admin.chat.service;

import com.example.admin.chat.modelo.Mensagem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by admin on 06/10/17.
 */

public interface ChatService {

    @POST("polling")
    Call<Void> enviarMensagem(@Body Mensagem mensagem);

    @GET("polling")
    Call<Mensagem> ouvirMensagem();

}
