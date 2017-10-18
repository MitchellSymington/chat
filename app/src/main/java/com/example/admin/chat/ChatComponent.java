package com.example.admin.chat;

import com.example.admin.chat.activity.MainActivity;
import com.example.admin.chat.adapter.MensagemAdapter;
import com.example.admin.chat.module.ChatModule;

import dagger.Component;

/**
 * Created by admin on 10/10/17.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);
}
