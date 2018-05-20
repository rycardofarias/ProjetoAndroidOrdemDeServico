package com.projetoengenharia.projetoengenharia;

import android.widget.Filter;

import com.projetoengenharia.projetoengenharia.adapter.AdapterCliente;
import com.projetoengenharia.projetoengenharia.model.Cliente;

import java.util.ArrayList;

public class FiltroCustomizado extends Filter {

    ArrayList<Cliente> filtarLista;
    AdapterCliente adapterCliente;

    public FiltroCustomizado(ArrayList<Cliente> filtarLista, AdapterCliente adapterCliente){
        this.filtarLista = filtarLista;
        this.adapterCliente = adapterCliente;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults filterResults = new FilterResults();
        if(charSequence != null && charSequence.length()>0){
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<Cliente> clienteFiltrado = new ArrayList<>();
            for (int i=0;i<filtarLista.size(); i++){
                if(filtarLista.get(i).getNome().toUpperCase().contains(charSequence)){
                    clienteFiltrado.add(filtarLista.get(i));
                }
            }
            filterResults.count=clienteFiltrado.size();
            filterResults.values=clienteFiltrado;
        }else{
            filterResults.count = filtarLista.size();
            filterResults.values = filtarLista;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapterCliente.clientes = (ArrayList<Cliente>) filterResults.values;
        adapterCliente.notifyDataSetChanged();
    }
}
