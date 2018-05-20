package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.adapter.AdapterCliente;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;
import com.projetoengenharia.projetoengenharia.model.Cliente;

import java.util.ArrayList;

public class ListaClientesActivity extends AppCompatActivity {

    private Button btnAdicionarcliente;
    private Button btnVoltarCliente;
    ListView lvCliente;
    AdapterCliente adapterCliente;
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        lvCliente = (ListView) findViewById(R.id.lvClientesId);
        adapterCliente = new AdapterCliente(this, getCliente());
        lvCliente.setAdapter(adapterCliente);

        /*lvCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Cliente clienteEnviado = (Cliente) adapterCliente.getItem(i);
                Intent x = new Intent(ListaClientesActivity.this, CadastroClienteActivity.class);
                x.putExtra("cliente-enviado", clienteEnviado);
                startActivity(x);
                startActivity(new Intent(ListaClientesActivity.this, CadastroClienteActivity.class));
            }
        }); */

        sv = (SearchView) findViewById(R.id.mSearch);
        //instanciando botões
        btnAdicionarcliente = findViewById(R.id.btnAdicionarClienteId);
        btnVoltarCliente = findViewById(R.id.btnVoltarClienteId);

        //criando ação de click do botão
        btnAdicionarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaClientesActivity.this, CadastroClienteActivity.class));
            }
        });
        btnVoltarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaClientesActivity.this, MainActivity.class));
                finish();
            }
        });

        // teste
        new ClienteController(this).buscarPeloId( 1);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterCliente.getFilter().filter(s);
                return false;
            }
        });

    }
    public void editarContatoPeloId(int idCliente){
        final ClienteController clienteController = new ClienteController(this);
        final Cliente cliente = clienteController.buscarPeloId((Integer) adapterCliente.getItem(idCliente));
    }

    //
    private ArrayList<Cliente> getCliente() {
        ArrayList <Cliente> clientes2 = (ArrayList<Cliente>) new ClienteController(this).listCliente();
        System.out.println(clientes2);
        return clientes2;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clientes, menu);
        return true;
    }
}
