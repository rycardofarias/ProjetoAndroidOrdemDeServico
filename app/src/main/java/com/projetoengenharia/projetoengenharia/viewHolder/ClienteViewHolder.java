package com.projetoengenharia.projetoengenharia.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.projetoengenharia.projetoengenharia.ItemClickListener;
import com.projetoengenharia.projetoengenharia.R;

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
