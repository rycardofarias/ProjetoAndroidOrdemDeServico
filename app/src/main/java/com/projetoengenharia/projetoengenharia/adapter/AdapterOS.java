package com.projetoengenharia.projetoengenharia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.activity.Editar_Ordem_Servico_Activity;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;
import com.projetoengenharia.projetoengenharia.ItemClickListener;
import com.projetoengenharia.projetoengenharia.activity.MainActivity;
import com.projetoengenharia.projetoengenharia.viewHolder.OrdemServicoViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Ricardo Farias on 01/05/2018.
 */

public class AdapterOS extends BaseAdapter implements Filterable {

    Context context;
    List<OrdemServico> ordemServicos;
    LayoutInflater inflater;

    public AdapterOS(Context context, List<OrdemServico> ordemServicos){
        this.context =context;
        this.ordemServicos = ordemServicos;
    }

    @Override
    public int getCount() {
        return ordemServicos.size();
    }

    @Override
    public Object getItem(int i) {
        return ordemServicos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.modelo_ordem_servico, null);
        }

        //vincular dados as visualizações
        OrdemServicoViewHolder  holder = new OrdemServicoViewHolder(view);

        String eximarcamodelo = ((ordemServicos.get(i).getMarca()+" "+ordemServicos.get(i).getModelo()));

        holder.getExibiNumeroOS().setText(String.valueOf(("Numero da OS - "+ordemServicos.get(i).getNumero_ordem_servico())));
        holder.getExibiModeloMarca().setText(eximarcamodelo);
        holder.getExibiCliente().setText(ordemServicos.get(i).getCliente().getNome());
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String dataCorrigida = formato.format(ordemServicos.get(i).getData_entrada());
        holder.getExibiData_entrada().setText(dataCorrigida);
        holder.getExibiStatus().setText(ordemServicos.get(i).getStatus_celular());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                OrdemServico ordemServicoEnviado = (OrdemServico) ordemServicos.get(i);
                Intent y = new Intent(v.getContext(), Editar_Ordem_Servico_Activity.class);
                y.putExtra("os-enviado", ordemServicoEnviado);
                context.startActivity(y);
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
