package com.projetoengenharia.projetoengenharia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;
import com.projetoengenharia.projetoengenharia.contoller.OS_Controller;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarOrdemServicoActivity extends AppCompatActivity {

    private boolean validacao;
    private EditText numeroOS;
    private Spinner escolher_cliente;
    private EditText modelo;
    private EditText marca;
    private EditText data_entrada;
    private EditText imei;
    private EditText acessorio;
    private EditText detalhes;
    private EditText defeito;
    private EditText valorPrevio;
    private EditText tecnico;
    private Button btnCadastrarOrdServ;
    private Button btnCancelarOrdServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_ordem_servico);
        numeroOS = (EditText) findViewById(R.id.edtNumeroOSId);

        escolher_cliente = (Spinner) findViewById(R.id.spinnerEscolherClienteId);

        modelo = (EditText) findViewById(R.id.edtModeloId);
        marca = (EditText) findViewById(R.id.edtMarcaId);
        imei = (EditText) findViewById(R.id.edtImeiId);
        acessorio = (EditText) findViewById(R.id.edtAcessoriosId);
        detalhes = (EditText) findViewById(R.id.edtDetalhesId);
        defeito = (EditText) findViewById(R.id.edtDefeitoId);
        valorPrevio = (EditText) findViewById(R.id.edtValorPrevioId);
        tecnico = (EditText) findViewById(R.id.edtTecnicoId);
        btnCadastrarOrdServ = (Button) findViewById(R.id.btnCadastraOrdServId);
        btnCancelarOrdServ = (Button) findViewById(R.id.btnCancelarOId);
        ClienteController clienteController = new ClienteController(CadastrarOrdemServicoActivity.this);
        // Array Adapter que é definido como adapter do spinner
        ArrayAdapter adapter = new ArrayAdapter(CadastrarOrdemServicoActivity.this, android.R.layout.simple_spinner_item,clienteController.listCliente());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        escolher_cliente.setAdapter(adapter);

        btnCancelarOrdServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnCadastrarOrdServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = new Cliente();

                Integer osNumero = Integer.valueOf(numeroOS.getText().toString());

                Cliente osCliente = (Cliente) escolher_cliente.getSelectedItem();
                int id = osCliente.getId();

                String osModelo = modelo.getText().toString();
                String osMarca = marca.getText().toString();
                String osImei = imei.getText().toString();
                String osAcessorios = acessorio.getText().toString();
                String osDetalhes = detalhes.getText().toString();
                String osDefeito = defeito.getText().toString();
                String osValorPrevio = valorPrevio.getText().toString();
                String osTecnico = tecnico.getText().toString();


                validarCampos();
                if (validacao == true) {
                    OrdemServico ordemServico = new OrdemServico();

                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    Date data = new Date();
                    ordemServico.setData_entrada(data);
                    cliente.setId(id);
                    ordemServico.setCliente(cliente);

                    ordemServico.setNumero_ordem_servico(osNumero);
                    ordemServico.setModelo(osModelo);
                    ordemServico.setMarca(osMarca);
                    ordemServico.setIMEI(Integer.valueOf(osImei));
                    ordemServico.setAcessorios(osAcessorios);
                    ordemServico.setDetalhes(osDetalhes);
                    ordemServico.setDefeito_reclamacao(osDefeito);
                    ordemServico.setValor_previo(Double.valueOf(osValorPrevio));
                    ordemServico.setTecnico_responsavel(osTecnico);
                    ordemServico.setStatus_celular("Aberta");
                    boolean criadoComSucesso = new OS_Controller(CadastrarOrdemServicoActivity.this).create(ordemServico);

                    if (criadoComSucesso) {
                        Toast.makeText(CadastrarOrdemServicoActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CadastrarOrdemServicoActivity.this, ListaOrdemServicoActivity.class));
                        finish();
                    } else
                        Toast.makeText(CadastrarOrdemServicoActivity.this, "Não foi abrir uma uma nova Ordem de Serviço. Vefique se não há um aparelho com um mesmo numero de OS ou IMEI", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void validarCampos() {

        validacao = false;

        if (numeroOS.getText().toString().trim().equals("") || numeroOS.getText().toString() == null) {
            validacao = false;
            numeroOS.setError("Campo obrigatorio");
            numeroOS.requestFocus();

        } else if (modelo.getText().toString().trim().equals("") || modelo.getText().toString() == null) {
            validacao = false;
            modelo.setError("Campo obrigatorio");
            modelo.requestFocus();

        } else if (marca.getText().toString().trim().equals("") || marca.getText().toString() == null) {
            validacao = false;
            marca.setError("Campo obrigatorio");
            marca.requestFocus();

        }else if (imei.getText().toString().trim().equals("") || imei.getText().toString() == null) {
            validacao = false;
            imei.setError("Campo obrigatorio");
            imei.requestFocus();
        }else if (acessorio.getText().toString().trim().equals("") || acessorio.getText().toString() == null) {
            validacao = false;
            acessorio.setError("Campo obrigatorio");
            acessorio.requestFocus();
        } else if (defeito.getText().toString().trim().equals("") || defeito.getText().toString() == null) {
            validacao = false;
            defeito.setError("Campo obrigatorio");
            defeito.requestFocus();
        } else if (detalhes.getText().toString().trim().equals("") || detalhes.getText().toString() == null) {
            validacao = false;
            detalhes.setError("Campo obrigatorio");
            detalhes.requestFocus();
        }  else if (valorPrevio.getText().toString().trim().equals("") || valorPrevio.getText().toString() == null) {
            validacao = false;
            valorPrevio.setError("Campo obrigatorio");
            valorPrevio.requestFocus();
        } else if (tecnico.getText().toString().trim().equals("") || tecnico.getText().toString() == null) {
            validacao = false;
            tecnico.setError("Campo obrigatorio");
            tecnico.requestFocus();
        } else
            validacao = true;
    }
}
