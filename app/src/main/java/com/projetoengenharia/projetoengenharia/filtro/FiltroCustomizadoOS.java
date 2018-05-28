package com.projetoengenharia.projetoengenharia.filtro;

import android.widget.Filter;

import com.projetoengenharia.projetoengenharia.adapter.AdapterOS;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;

import java.util.ArrayList;

public class FiltroCustomizadoOS extends Filter {

    ArrayList<OrdemServico> filtarLista;
    AdapterOS adapterOS;

    public FiltroCustomizadoOS(ArrayList<OrdemServico> filtarLista, AdapterOS adapterOS){
        this.filtarLista = filtarLista;
        this.adapterOS = adapterOS;
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence charSequence) {
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if(charSequence != null && charSequence.length()>0){
            charSequence = charSequence.toString().toUpperCase();
            ArrayList<OrdemServico> ordemServicoFiltrado = new ArrayList<>();
            for (int i=0;i<filtarLista.size(); i++){
                if(filtarLista.get(i).getNumero_ordem_servico().toUpperCase().contains(charSequence)){
                    ordemServicoFiltrado.add(filtarLista.get(i));
                }else if(filtarLista.get(i).getCliente().getNome().toUpperCase().contains(charSequence)){
                    ordemServicoFiltrado.add(filtarLista.get(i));
                }
            }
            filterResults.count=ordemServicoFiltrado.size();
            filterResults.values=ordemServicoFiltrado;
        }else{
            filterResults.count = filtarLista.size();
            filterResults.values = filtarLista;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        adapterOS.ordemServicos = (ArrayList<OrdemServico>) filterResults.values;
        adapterOS.notifyDataSetChanged();
    }
}
