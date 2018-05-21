package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
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
    AdapterOS adapterOS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__ordem__servico_);

        Intent y = getIntent();
        editarOS = (OrdemServico) y.getSerializableExtra("os-enviado");

        opcoes.add("Em andamento");
        opcoes.add("Concluída");

        status = findViewById(R.id.spinnerStatusId);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nomeOp = adapterView.getItemAtPosition(i).toString();
                if (nomeOp == "Concluída"){
                    valor_final.setVisibility(View.VISIBLE);
                }
                else
                    valor_final.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        valor_final = findViewById(R.id.txtValorFinalId);
        numeroOSMarcaModelo= findViewById(R.id.txtNumeroMarcaModeloId);
        txtCliente= findViewById(R.id.txtClienteId);
        modelo= findViewById(R.id.txtModeloId);
        marca= findViewById(R.id.txtMarcaId);
        data_entrada= findViewById(R.id.txtDataEntradaId);
        imei= findViewById(R.id.txtImeiId);
        acessorio= findViewById(R.id.txtAcessoriosId);
        detalhes= findViewById(R.id.txtDetalhesId);
        defeito= findViewById(R.id.txtDefeitoId);
        valorPrevio= findViewById(R.id.txtValorPrevioId);
        tecnico= findViewById(R.id.txtTecnicoResponsavelId);

        btnAlterarOrdServ = findViewById(R.id.btnOrdemServicoAlterarId);
        btnvoltarOrdServ = findViewById(R.id.btnOrdemServicoVoltarId);

        if(editarOS != null){

            final OS_Controller os_controller = new OS_Controller(this);
            final OrdemServico ordemServico = os_controller.buscapeloId(editarOS.getId());

            numeroOSMarcaModelo.setText("Smartphone "+ ordemServico.getMarca()+" "+ordemServico.getModelo()+" - "+ordemServico.getNumero_ordem_servico());
            txtCliente.setText(editarOS.getCliente().getNome());
            //System.out.println("txt Cliente "+ txtCliente);
            //System.out.println(ordemServico.getCliente().getNome().toString());
            modelo.setText(ordemServico.getModelo());
            marca.setText(ordemServico.getMarca());
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            data_entrada.setText(String.valueOf( formato.format(ordemServico.getData_entrada())));
            imei.setText(ordemServico.getIMEI());
            acessorio.setText(ordemServico.getAcessorios());
            detalhes.setText(ordemServico.getDetalhes());
            defeito.setText(ordemServico.getDefeito_reclamacao());
            valorPrevio.setText(ordemServico.getValor_previo());
            tecnico.setText(ordemServico.getTecnico_responsavel());

            valor_final.setText("");

            ordemServico.setId(editarOS.getId());


            System.out.println("ord ser"+ordemServico.toString());
            System.out.println("edt os"+editarOS.toString());
            Toast.makeText(Editar_Ordem_Servico_Activity.this, "legal //////", Toast.LENGTH_LONG).show();

         }
         btnAlterarOrdServ.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                     OrdemServico novoOS = new OrdemServico();

                     novoOS.setStatus_celular(nomeOp);
                     novoOS.setValor_final(valor_final.getText().toString());
                     novoOS.setId(editarOS.getId());
                     validarCampos();
                 if (validacao==true){
                     boolean finalizarOS = new OS_Controller(Editar_Ordem_Servico_Activity.this).updateOS(novoOS);
                     if (finalizarOS) {
                         startActivity(new Intent(Editar_Ordem_Servico_Activity.this, ListaOrdemServicoActivity.class));
                         Toast.makeText(Editar_Ordem_Servico_Activity.this, "funcionou //////", Toast.LENGTH_LONG).show();
                         finish();
                     }else
                         Toast.makeText(Editar_Ordem_Servico_Activity.this, "NAAAAAOOO funcionou //////", Toast.LENGTH_LONG).show();

                 }
            }
        });
    }
    private void validarCampos() {

        validacao = false;

        if (nomeOp=="Concluída") {

            if (valor_final.getText().toString().trim().equals("") || valor_final.getText().toString() == null) {
                validacao = false;
                valor_final.setError("Campo obrigatorio");
                valor_final.requestFocus();

            } else
                validacao = true;
        }else
            validacao = true;
    }

}
