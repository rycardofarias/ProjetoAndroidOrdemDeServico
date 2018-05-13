package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView btnCliente;
    private ImageView btnOrdemServico;
    private ImageView btnprestacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*Cliente cliente = new Cliente();
        ClienteController clienteController = new ClienteController(getApplicationContext());
        Date date = new Date();
        Endereco endereco = new Endereco();

        date.setTime(2000-12-12);

        endereco.setEstado("Para");
        endereco.setCidade("Pa");
        endereco.setBairro("ra");
        endereco.setLogadouro("gfsgPara");
        endereco.setNumero(232);
        cliente.setEndereco(endereco);


        cliente.setNome("Ricardo");
        cliente.setCPF("25425");
        cliente.setDataNascimento(date);
        cliente.setTelefone("4536457");
        cliente.setEmail("sdag");

        clienteController.create(cliente);*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // instanciando botões
        btnCliente = (ImageView) findViewById(R.id.btnEditarClienteId);
        btnOrdemServico= (ImageView) findViewById(R.id.btnOrdemServicoId);

        //criando a ação de click de botão
        /*btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, ListaClientesActivity.class));
            }
        });*/
        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListaClientesActivity.class));
            }
        });
        btnOrdemServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListaOrdemServicoActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(MainActivity.this, Editar_Ordem_Servico_Activity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void contadorDeRegistro() {
        String msg = "";
        int contador = new ClienteController(this).totalclientes();

        //TextView txtContadorCliente = findViewById(R.id.contadorClienteId);

        if (contador == 0) {
            msg = "Nenhum cliente cadastrado";
        }  else {
            msg = contador + " Clientes cadastrados";
        }
        //txtContadorCliente.setText(msg);
    }

}
