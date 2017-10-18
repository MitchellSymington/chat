package com.example.admin.chat.module;

import android.app.Application;

import com.example.admin.chat.service.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 10/10/17.
 */

@Module
public class ChatModule {

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public EventBus getEventBus(){
        return EventBus.builder().build();
    }

    @Provides
    public ChatService getChatService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://protected-spire-41057.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ChatService chatService = retrofit.create(ChatService.class);

        return chatService;
    }

    @Provides
    public Picasso getPicasso(){
        Picasso picasso = new Picasso.Builder(app).build();
        return picasso;
    }
}
