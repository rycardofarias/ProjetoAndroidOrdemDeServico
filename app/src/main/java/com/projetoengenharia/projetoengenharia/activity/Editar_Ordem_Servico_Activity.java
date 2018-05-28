package com.projetoengenharia.projetoengenharia.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.adapter.AdapterOS;
import com.projetoengenharia.projetoengenharia.contoller.OS_Controller;
import com.projetoengenharia.projetoengenharia.email.Mail;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Editar_Ordem_Servico_Activity extends AppCompatActivity {

    private boolean validacao;

    private EditText valor_final;
    private Spinner status;

    private TextView numeroOSMarcaModelo;
    private TextView txtCliente;
    private TextView modelo;
    private TextView marca;
    private TextView data_entrada;
    private TextView imei;
    private TextView acessorio;
    private TextView detalhes;
    private TextView defeito;
    private TextView valorPrevio;
    private TextView tecnico;
    private Button btnAlterarOrdServ;
    private Button btnvoltarOrdServ;

    private List<String> opcoes = new ArrayList<String>();
    private String nomeOp;
    OrdemServico editarOS;
    private String nomeCliente;
    private String emailCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__ordem__servico_);

        Intent y = getIntent();
        editarOS = (OrdemServico) y.getSerializableExtra("os-enviado");

        status = findViewById(R.id.spinnerStatusId);
        valor_final = findViewById(R.id.txtValorFinalId);
        numeroOSMarcaModelo = findViewById(R.id.txtNumeroMarcaModeloId);
        txtCliente = findViewById(R.id.txtClienteId);
        modelo = findViewById(R.id.txtModeloId);
        marca = findViewById(R.id.txtMarcaId);
        data_entrada = findViewById(R.id.txtDataEntradaId);
        imei = findViewById(R.id.txtImeiId);
        acessorio = findViewById(R.id.txtAcessoriosId);
        detalhes = findViewById(R.id.txtDetalhesId);
        defeito = findViewById(R.id.txtDefeitoId);
        valorPrevio = findViewById(R.id.txtValorPrevioId);
        tecnico = findViewById(R.id.txtTecnicoResponsavelId);
        btnAlterarOrdServ = findViewById(R.id.btnOrdemServicoAlterarId);

        if (editarOS != null) {

            final OS_Controller os_controller = new OS_Controller(this);
            final OrdemServico ordemServico = os_controller.buscapeloId(editarOS.getId());

            nomeCliente = ordemServico.getCliente().getNome();
            emailCliente = ordemServico.getCliente().getEmail();
            //System.out.println("email "+email);
            //System.out.println("nome "+nomeEmail);

            String testeStatus = (ordemServico.getStatus_celular());
            System.out.println("status " + testeStatus);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoes);
            ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            opcoes.add("Aberta");
            opcoes.add("Concluída");
            if (ordemServico.getValor_final().length() <= 0) {
                System.out.println("entrou no if ");
                status.setEnabled(true);
                valor_final.setEnabled(true);
            } else {
                System.out.println("entrou no else ");
                opcoes.remove(0);
                status.setEnabled(false);
                valor_final.setEnabled(false);
            }
            status.setAdapter(spinnerArrayAdapter);
            //Método do Spinner para capturar o item selecionado
            status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    nomeOp = adapterView.getItemAtPosition(i).toString();
                    if (nomeOp == "Concluída") {
                        valor_final.setVisibility(View.VISIBLE);
                    } else {
                        valor_final.setVisibility(View.INVISIBLE);
                        valor_final.setText(null);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            valor_final.setText(ordemServico.getValor_final());
            numeroOSMarcaModelo.setText("Smartphone " + ordemServico.getMarca() + " " + ordemServico.getModelo() + " - " + ordemServico.getNumero_ordem_servico());
            txtCliente.setText(editarOS.getCliente().getNome());
            modelo.setText(ordemServico.getModelo());
            marca.setText(ordemServico.getMarca());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            data_entrada.setText(String.valueOf(formato.format(ordemServico.getData_entrada())));
            imei.setText(ordemServico.getIMEI());
            acessorio.setText(ordemServico.getAcessorios());
            detalhes.setText(ordemServico.getDetalhes());
            defeito.setText(ordemServico.getDefeito_reclamacao());
            valorPrevio.setText(ordemServico.getValor_previo());
            tecnico.setText(ordemServico.getTecnico_responsavel());

            ordemServico.setId(editarOS.getId());
        }
        btnAlterarOrdServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OrdemServico novoOS = new OrdemServico();

                novoOS.setStatus_celular(nomeOp);
                novoOS.setValor_final(valor_final.getText().toString());
                novoOS.setId(editarOS.getId());

                validarCampos();
                if (validacao == true) {
                    boolean finalizarOS = new OS_Controller(Editar_Ordem_Servico_Activity.this).updateOS(novoOS);
                    if (finalizarOS) {
                        if (nomeCliente != null && emailCliente!=null) {
                            enviarEmail(nomeCliente, emailCliente);
                            startActivity(new Intent(Editar_Ordem_Servico_Activity.this, ListaOrdemServicoActivity.class));
                            Toast.makeText(Editar_Ordem_Servico_Activity.this, "Ordem de Serviço finalizado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                       }else
                            Toast.makeText(Editar_Ordem_Servico_Activity.this, "Não foi possivel enviar o e-mail", Toast.LENGTH_LONG).show();

                    } else
                        Toast.makeText(Editar_Ordem_Servico_Activity.this, "Ocorreu um erro", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void validarCampos() {

        validacao = false;

        if (nomeOp == "Concluída") {

            if (valor_final.getText().toString().trim().equals("") || valor_final.getText().toString() == null) {
                validacao = false;
                valor_final.setError("Campo obrigatorio");
                valor_final.requestFocus();

            } else
                validacao = true;
        } else
            validacao = true;
    }
    private void enviarEmail(final String nome, final String email) {

        if (isOnline()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Mail m = new Mail("email@gmail.com", "senha");

                    String[] toArr = {email};
                    m.setTo(toArr);

                    m.setFrom(email);
                    m.setSubject("MD Informatica, ordem de serviço de concluída!");
                    m.setBody(nome + ", seu aparelho foi consertado!");

                    try {
                        //m.addAttachment("pathDoAnexo");//anexo opcional
                        m.send();
                    } catch (RuntimeException rex) {
                        rex.printStackTrace();
                    }//erro ignorado
                    catch (Exception e) {
                        e.printStackTrace();
                        //tratar algum outro erro aqui
                    }

                    //System.exit(0);
                }
            }).start();
        } else {
            //Toast.makeText(getApplicationContext(), "Não estava online para enviar e-mail!", Toast.LENGTH_SHORT).show();
            //System.exit(0);
        }
    }

    public boolean isOnline() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "Erro ao verificar se estava online! ", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
