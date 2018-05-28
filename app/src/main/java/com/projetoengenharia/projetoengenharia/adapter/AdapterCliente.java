package com.projetoengenharia.projetoengenharia.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.projetoengenharia.projetoengenharia.filtro.FiltroCustomizadoCliente;
import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.activity.CadastroClienteActivity;
import com.projetoengenharia.projetoengenharia.activity.ListaClientesActivity;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.viewHolder.ClienteViewHolder;

import java.util.ArrayList;

/**
 * Created by Ricardo Farias on 26/04/2018.
 */

public class AdapterCliente extends BaseAdapter implements Filterable {

    Context context;
    public ArrayList<Cliente> clientes;
    LayoutInflater inflater;

    ArrayList<Cliente> filtrarListaCliente;
    FiltroCustomizadoCliente filtroCliente;

    private ImageView btnEditar;
    private ImageView btnDeletar;

    public AdapterCliente(Context context, ArrayList<Cliente> clientes){
        this.context = context;
        this.clientes = clientes;
        this.filtrarListaCliente = clientes;
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
        holder.getNomeCliente().setText(clientes.get(i).getNome());

        btnEditar = (ImageView)view.findViewById(R.id.btnEditarClienteId);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente clienteEnviado = (Cliente) clientes.get(i);
                Intent x = new Intent(view.getContext(), CadastroClienteActivity.class);
                x.putExtra("cliente-enviado", clienteEnviado);
                context.startActivity(x);
            }
        });
        btnDeletar = view.findViewById(R.id.btnDeleteClienteId);
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //v.getContext().startActivity(new Intent(v.getContext(), ListaClientesActivity.class);
               new AlertDialog.Builder(v.getContext())
                        .setTitle("Deletando cliente")
                        .setMessage("Tem certeza que deseja deletar esse cliente?")
                        .setPositiveButton("sim",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int x) {
                                        boolean deleteCliente = new ClienteController(context).deleteCliente(clientes.get(i).getId());
                                        context.startActivity(new Intent(context, ListaClientesActivity.class));
                                        ((ListaClientesActivity)context).finish();
                                    }
                                })
                        .setNegativeButton("não", null)
                        .show();
            }
        });
        return view;
    }
    @Override
    public Filter getFilter() {

        if (filtroCliente == null ){
            filtroCliente = new FiltroCustomizadoCliente(filtrarListaCliente, this);
        }
        return filtroCliente;
    }

}
