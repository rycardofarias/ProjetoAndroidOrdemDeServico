package com.projetoengenharia.projetoengenharia.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.projetoengenharia.projetoengenharia.Mascara;
import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.contoller.ClienteController;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.model.Endereco;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroClienteActivity extends AppCompatActivity {

    private boolean validacao;
    private Context context2;
    private EditText nomeCliente;
    private EditText cpf;
    private EditText dataNascimento;
    private EditText telefone;
    private EditText email;
    private EditText estado;
    private EditText cidade;
    private EditText bairro;
    private EditText rua;
    private EditText numero;

    private Button btnAdicionar;
    private Button btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        nomeCliente = (EditText) findViewById(R.id.edtNomeClienteId);
        cpf = (EditText) findViewById(R.id.editTextCpfId);
        cpf.addTextChangedListener(Mascara.insert("###.###.###-##",cpf));
        dataNascimento = (EditText) findViewById(R.id.edtDataNascimentoId);
        dataNascimento.addTextChangedListener(Mascara.insert("##/##/####", dataNascimento));
        telefone = (EditText) findViewById(R.id.edtTelefoneId);
        telefone.addTextChangedListener(Mascara.insert("(##)#####-####", telefone));
        email = (EditText) findViewById(R.id.edtEmailId);
        estado = (EditText) findViewById(R.id.edtEstadoId);
        cidade = (EditText) findViewById(R.id.edtCidadeId);
        bairro = (EditText) findViewById(R.id.edtBairroId);
        rua = (EditText) findViewById(R.id.edtRuaId);
        numero = (EditText) findViewById(R.id.edtNumeroId);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionarCliId);
        btnCancelar = (Button) findViewById(R.id.btnCancelarClienteId);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String clienteNome = nomeCliente.getText().toString();
                String clienteCpf = cpf.getText().toString();

                String clienteDataNascimento = dataNascimento.getText().toString();

                String clienteTelefone = telefone.getText().toString();
                String clienteEmail = email.getText().toString();
                String clienteEstado = estado.getText().toString();
                String clienteCidade = cidade.getText().toString();
                String clienteBairro = bairro.getText().toString();
                String clienteRua = rua.getText().toString();
                int clienteNumero = Integer.parseInt(numero.getText().toString());

                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date data_nascimento = formato.parse(clienteDataNascimento);
                    cliente.setDataNascimento(data_nascimento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                validarCampos();

                if (validacao == true) {

                    endereco.setEstado(clienteEstado);
                    endereco.setCidade(clienteCidade);
                    endereco.setBairro(clienteBairro);
                    endereco.setLogadouro(clienteRua);
                    endereco.setNumero(clienteNumero);
                    cliente.setEndereco(endereco);
                    cliente.setNome(clienteNome);
                    cliente.setCPF(clienteCpf);
                    cliente.setTelefone(clienteTelefone);
                    cliente.setEmail(clienteEmail);

                    boolean criadoComSucesso = new ClienteController(CadastroClienteActivity.this).create(cliente);

                    if (criadoComSucesso) {
                        Toast.makeText(CadastroClienteActivity.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                        //((ListaClientesActivity) context).contadorDeRegistro();
                        startActivity(new Intent(CadastroClienteActivity.this, ListaClientesActivity.class));
                        finish();
                    } else
                        Toast.makeText(CadastroClienteActivity.this, "não foi possível efetuar o cadastro. Verifique os dados CPF, Telefone e E-mail se não há já um cliente cadastrado com esses dados", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void validarCampos() {
        validacao = false;

        if (nomeCliente.getText().toString().trim().equals("") || nomeCliente.getText().toString() == null) {
            validacao = false;
            nomeCliente.setError("Campo obrigatorio");
            nomeCliente.requestFocus();

        } else if (cpf.getText().toString().trim().equals("") || cpf.getText().toString() == null) {
            validacao = false;
            cpf.setError("Campo obrigatorio");
            cpf.requestFocus();

        } else if (dataNascimento.getText().toString().trim().equals("") || dataNascimento.getText().toString() == null) {
            validacao = false;
            dataNascimento.setError("Campo obrigatorio");
            dataNascimento.requestFocus();

        }else if (telefone.getText().toString().trim().equals("") || telefone.getText().toString() == null) {
            validacao = false;
            telefone.setError("Campo obrigatorio");
            telefone.requestFocus();
        }else if ((email.getText().toString().trim().equals("") || email.getText().toString() == null)) {
            validacao = false;
            email.setError("Campo obrigatorio");
            email.requestFocus();
        }else if (estado.getText().toString().trim().equals("") || estado.getText().toString() == null) {
            validacao = false;
            estado.setError("Campo obrigatorio");
            estado.requestFocus();
        } else if (cidade.getText().toString().trim().equals("") || cidade.getText().toString() == null) {
            validacao = false;
            cidade.setError("Campo obrigatorio");
            cidade.requestFocus();
        }  else if (bairro.getText().toString().trim().equals("") || bairro.getText().toString() == null) {
            validacao = false;
            bairro.setError("Campo obrigatorio");
            bairro.requestFocus();
        } else if (rua.getText().toString().trim().equals("") || rua.getText().toString() == null) {
            validacao = false;
            rua.setError("Campo obrigatorio");
            rua.requestFocus();
        } else if (numero.getText().toString().trim().equals("") || numero.getText().toString() == null) {
            validacao = false;
            numero.setError("Campo obrigatorio");
            numero.requestFocus();
        } else
            validacao = true;

    }
}