package com.projetoengenharia.projetoengenharia.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ricardo Farias on 19/04/2018.
 */

public class Cliente implements Serializable{

    private Integer id;

    private String nome;
    private String CPF;
    private Date DataNascimento;
    private String Telefone;
    private String Email;

    private Endereco endereco;

    public Cliente(){

    }

    @Override
    public String toString()
    {
        return nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
