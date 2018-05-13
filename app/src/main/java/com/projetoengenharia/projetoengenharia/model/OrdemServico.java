package com.projetoengenharia.projetoengenharia.model;

import java.util.Date;

/**
 * Created by Ricardo Farias on 19/04/2018.
 */

public class OrdemServico {

    private Integer id;

    private Cliente cliente;

    private int numero_ordem_servico;
    private String modelo;
    private String marca;
    private Date data_entrada;
    private Date previsao_saida;
    private Integer IMEI;
    private String acessorios;
    private String detalhes;
    private String defeito_reclamacao;
    private Double valor_previo;
    private String tecnico_responsavel;

    private String status_celular;
    private Double valor_final;

    public OrdemServico(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumero_ordem_servico() {
        return numero_ordem_servico;
    }

    public void setNumero_ordem_servico(int numero_ordem_servico) {
        this.numero_ordem_servico = numero_ordem_servico;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Date data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Date getPrevisao_saida() {
        return previsao_saida;
    }

    public void setPrevisao_saida(Date previsao_saida) {
        this.previsao_saida = previsao_saida;
    }

    public Integer getIMEI() {
        return IMEI;
    }

    public void setIMEI(Integer IMEI) {
        this.IMEI = IMEI;
    }

    public String getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(String acessorios) {
        this.acessorios = acessorios;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getDefeito_reclamacao() {
        return defeito_reclamacao;
    }

    public void setDefeito_reclamacao(String defeito_reclamacao) {
        this.defeito_reclamacao = defeito_reclamacao;
    }

    public Double getValor_previo() {
        return valor_previo;
    }

    public void setValor_previo(Double valor_previo) {
        this.valor_previo = valor_previo;
    }

    public String getTecnico_responsavel() {
        return tecnico_responsavel;
    }

    public void setTecnico_responsavel(String tecnico_responsavel) {
        this.tecnico_responsavel = tecnico_responsavel;
    }

    public String getStatus_celular() {
        return status_celular;
    }

    public void setStatus_celular(String status_celular) {
        this.status_celular = status_celular;
    }

    public Double getValor_final() {
        return valor_final;
    }

    public void setValor_final(Double valor_final) {
        this.valor_final = valor_final;
    }
}
