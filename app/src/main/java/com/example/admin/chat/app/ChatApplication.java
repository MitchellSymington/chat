package com.example.admin.chat.app;

import android.app.Application;

import com.example.admin.chat.ChatComponent;
import com.example.admin.chat.DaggerChatComponent;
import com.example.admin.chat.module.ChatModule;

/**
 * Created by admin on 10/10/17.
 */

public class ChatApplication extends Application{

    private ChatComponent component;

    public void onCreate(){
        component = DaggerChatComponent.builder()
                                        .chatModule(new ChatModule(this))
                                        .build();
    }

    public ChatComponent getComponente() {
        return component;
    }

}
