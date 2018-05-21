package com.projetoengenharia.projetoengenharia.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.projetoengenharia.projetoengenharia.ItemClickListener;
import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.activity.CadastroClienteActivity;

/**
 * Created by Ricardo Farias on 23/04/2018.
 */

public class ClienteViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    private TextView nomeCliente;
    private ImageView btnEditar;
    private ImageView btnDeletar;

    private EditText email;

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view);
    }

    //ligando os dados dos campos/botões para as varieveis.
    public ClienteViewHolder(View v) {

        nomeCliente = (TextView) v.findViewById(R.id.exibiNumeroId);
        btnEditar = (ImageView)v.findViewById(R.id.btnEditarClienteId);
        btnDeletar = (ImageView) v.findViewById(R.id.btnDeleteClienteId);

        email = (EditText) v.findViewById(R.id.edtEmailId);
        v.setOnClickListener(this);

        //criando ações botões
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), CadastroClienteActivity.class));
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

    public ImageView getBtnEditar() {
        return btnEditar;
    }

    public void setBtnEditar(ImageView btnEditar) {
        this.btnEditar = btnEditar;
    }

    public ImageView getBtnDeletar() {
        return btnDeletar;
    }

    public void setBtnDeletar(ImageView btnDeletar) {
        this.btnDeletar = btnDeletar;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }
}
