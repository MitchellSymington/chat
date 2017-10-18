package com.example.admin.chat.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.chat.R;
import com.example.admin.chat.modelo.Mensagem;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 05/10/17.
 */

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;
    private int idDoCliente;

    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_imagem_mensagem)
    ImageView avatar;

    @Inject
    Picasso picasso;

    public MensagemAdapter (int idDoClinete,List<Mensagem> mensagens, Activity activity){
        this.mensagens = mensagens;
        this.activity = activity;
        this.idDoCliente = idDoClinete;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha;
        Mensagem mensagem = (Mensagem) getItem(position);
        int idDaMensagem = mensagem.getId();

        if(idDoCliente != mensagem.getId()){;
            linha = activity.getLayoutInflater().inflate(R.layout.mensagem_recebe,parent,false);
        }else{
            linha = activity.getLayoutInflater().inflate(R.layout.mensagem_envia,parent,false);
        }

        ButterKnife.bind(this,linha);

        picasso.with(activity).load("http://api.adorable.io/avatars/285" + idDaMensagem + ".png").into(avatar);
        texto.setText(mensagem.getTexto());
        return linha;

    }
}
