package com.projetoengenharia.projetoengenharia.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.projetoengenharia.projetoengenharia.ItemClickListener;
import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.activity.CadastroClienteActivity;
import com.projetoengenharia.projetoengenharia.model.Cliente;

import java.util.ArrayList;

/**
 * Created by Ricardo Farias on 23/04/2018.
 */

public class ClienteViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    private TextView nomeCliente;

    @Override
    public void onClick(View view) {
        //this.itemClickListener.onItemClick(view);
    }
    public ClienteViewHolder(View v) {

        nomeCliente = (TextView) v.findViewById(R.id.exibiNumeroId);

        v.setOnClickListener(this);

    }
    //gets e sets
    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TextView getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(TextView nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
