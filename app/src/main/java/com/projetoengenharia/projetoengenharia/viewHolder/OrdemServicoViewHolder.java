package com.projetoengenharia.projetoengenharia.viewHolder;

import android.view.View;
import android.widget.TextView;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.ItemClickListener;

/**
 * Created by Ricardo Farias on 01/05/2018.
 */

public class OrdemServicoViewHolder implements View.OnClickListener {
    ItemClickListener itemClickListener;

    private TextView exibiNumeroOS;
    private TextView exibiModeloMarca;
    private TextView exibiCliente;
    private TextView exibiData_entrada;
    private TextView exibiStatus;


    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view);
    }

    //ligando os dados dos campos/botões para as varieveis.
    public OrdemServicoViewHolder(View v){
        exibiNumeroOS = (TextView) v.findViewById(R.id.exibiNumeroId);
        exibiModeloMarca = (TextView) v.findViewById(R.id.exibiModeloMarcaId);
        exibiCliente = (TextView) v.findViewById(R.id.exibiClienteId);
        exibiData_entrada = (TextView) v.findViewById(R.id.exibiDataId);
        exibiStatus = (TextView) v.findViewById(R.id.exibiStatusId);
        v.setOnClickListener(this);

        //criando ações botões
    }
    //gets e sets
    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public TextView getExibiNumeroOS() {
        return exibiNumeroOS;
    }

    public void setExibiNumeroOS(TextView exibiNumeroOS) {
        this.exibiNumeroOS = exibiNumeroOS;
    }

    public TextView getExibiModeloMarca() {
        return exibiModeloMarca;
    }

    public void setExibiModeloMarca(TextView exibiModeloMarca) {
        this.exibiModeloMarca = exibiModeloMarca;
    }

    public TextView getExibiCliente() {
        return exibiCliente;
    }

    public void setExibiCliente(TextView exibiCliente) {
        this.exibiCliente = exibiCliente;
    }

    public TextView getExibiData_entrada() {
        return exibiData_entrada;
    }

    public void setExibiData_entrada(TextView exibiData_entrada) {
        this.exibiData_entrada = exibiData_entrada;
    }

    public TextView getExibiStatus() {
        return exibiStatus;
    }

    public void setExibiStatus(TextView exibiStatus) {
        this.exibiStatus = exibiStatus;
    }
}
