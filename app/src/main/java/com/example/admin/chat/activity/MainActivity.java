package com.example.admin.chat.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.input.InputManager;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.admin.chat.app.ChatApplication;
import com.example.admin.chat.ChatComponent;
import com.example.admin.chat.R;
import com.example.admin.chat.adapter.MensagemAdapter;
import com.example.admin.chat.callback.EnviarMensagemCallback;
import com.example.admin.chat.callback.OuvirMensagemCallback;
import com.example.admin.chat.event.MensagemEvent;
import com.example.admin.chat.modelo.Mensagem;
import com.example.admin.chat.service.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente = 1;

    @BindView(R.id.et_texto)
    EditText editText;
    @BindView(R.id.btn_enviar)
    Button button;
    @BindView(R.id.lv_mensagens)
    ListView listaDeMensagem;
    @BindView(R.id.iv_imagem_avatar)
    ImageView avatar;

    private List<Mensagem> mensagens;

    @Inject
    ChatService chatService;

    @Inject
    Picasso picasso;

    @Inject
    EventBus eventBus;

    private ChatComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        picasso.with(this).load("https://api.adorable.io/avatars/285/" + idDoCliente + ".png").into(avatar);

        ChatApplication app = (ChatApplication) getApplication();
        component = app.getComponente();
        component.inject(this);

        if(savedInstanceState != null){
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable("mensagens");
        }else{
            mensagens = new ArrayList<>();
        }

        MensagemAdapter mensagemAdapter = new MensagemAdapter(idDoCliente,mensagens,this);
        listaDeMensagem.setAdapter(mensagemAdapter);

        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagemCallback(eventBus,this));

        eventBus.register(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mensagens", (ArrayList<Mensagem>) mensagens);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @OnClick(R.id.btn_enviar)
    public void enviarMensagem(){
        chatService.enviarMensagem(new Mensagem(idDoCliente,editText.getText().toString())).enqueue(new EnviarMensagemCallback());
        editText.setText(null);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent mensagemEvent) {
        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagemCallback(eventBus,this));
    }

    @Subscribe
    public void colocaNaLista (MensagemEvent mensagemEvent) {
        mensagens.add(mensagemEvent.mensagem);
        MensagemAdapter adapter = new MensagemAdapter(idDoCliente,mensagens,this);
        listaDeMensagem.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }
}
