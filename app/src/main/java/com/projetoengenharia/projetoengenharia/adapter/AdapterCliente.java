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
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.ItemClickListener;
import com.projetoengenharia.projetoengenharia.activity.MainActivity;
import com.projetoengenharia.projetoengenharia.viewHolder.ClienteViewHolder;

import java.util.List;

/**
 * Created by Ricardo Farias on 26/04/2018.
 */

public class AdapterCliente extends BaseAdapter implements Filterable {

    Context context;
    List<Cliente> clientes;
    LayoutInflater inflater;

    public AdapterCliente(Context context, List<Cliente> clientes){
        this.context = context;
        this.clientes = clientes;
    }

    @Override
    public int getCount() {
        return clientes.size();
    }

    @Override
    public Object getItem(int i) {
        return clientes.get(i);
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
            view = inflater.inflate(R.layout.modelo_cliente, null);
        }

        //vincular dados as visualizações
        ClienteViewHolder holder = new ClienteViewHolder(view);
        //holder.getImg().setImageResource(categorias.get(i).getImagemCategoria());
        holder.getNomeCliente().setText(clientes.get(i).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), MainActivity.class));
            }
        });
        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
