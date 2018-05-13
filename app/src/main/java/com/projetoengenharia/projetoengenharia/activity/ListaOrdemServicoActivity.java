package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.adapter.AdapterOS;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;
import com.projetoengenharia.projetoengenharia.contoller.OS_Controller;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;

import java.util.ArrayList;

public class ListaOrdemServicoActivity extends AppCompatActivity {

    private Button btnAdicionarOrdemServico;
    private Button btnCancelarOrdemServico;
    ListView lvOS;
    AdapterOS adapterOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordem_servico);

        //instanciando os botoes
        btnAdicionarOrdemServico = (Button) findViewById(R.id.btnAdicionarOrdemServicoId);
        btnCancelarOrdemServico = (Button) findViewById(R.id.btnVoltarOrdemServicoId);

        final int contador = new ClienteController(this).totalclientes();

        //criando ação de click
        btnAdicionarOrdemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador>0) {
                    startActivity(new Intent(ListaOrdemServicoActivity.this, CadastrarOrdemServicoActivity.class));

                }
                else{
                    Toast.makeText(ListaOrdemServicoActivity.this, "Cadastre um Cliente antes de efetuar essa opção", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancelarOrdemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaOrdemServicoActivity.this, MainActivity.class));
                finish();
            }
        });
        lvOS = (ListView) findViewById(R.id.lvOrdemServicoId);
        adapterOS = new AdapterOS(this, getOS());
        lvOS.setAdapter(adapterOS);
    }
    private ArrayList<OrdemServico> getOS() {
        ArrayList <OrdemServico> os = (ArrayList<OrdemServico>) new OS_Controller(this).listOS();
        return os;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ordem_servico, menu);
        return true;
    }
}
