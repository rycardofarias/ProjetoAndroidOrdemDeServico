package com.projetoengenharia.projetoengenharia.activity.pdfActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.projetoengenharia.projetoengenharia.R;
import com.projetoengenharia.projetoengenharia.contoller.OS_Controller;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;
import com.projetoengenharia.projetoengenharia.pdf.RelatorioPDF;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RelatorioPdfActivity extends AppCompatActivity {

    private String[] header = {"Id", "Numero", "Data de entrada", "Marca","Modelo", "Cliente", "Status","Tecnico","Preço final"};
    //private String shortTexto = "Hora";
    private String longoTexto = " Relatorio de todas as Ordem de Serviços Abertas e Concluídas";
    private RelatorioPDF relatorioPDF;


    //private ArrayList<OrdemServico> ordemServicos=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_pdf);
        relatorioPDF = new RelatorioPDF(getApplicationContext());
        relatorioPDF.abriDocumento();
        relatorioPDF.adicionarMetaData("Relatorio Ordens de Serviços", "Teste", "Ricardo");
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dataConvertida = formato.format(date);
        relatorioPDF.adicionarTitulos("Relatorio", "Ordens de Serviços", dataConvertida);
        relatorioPDF.addParagrafo(longoTexto);
        relatorioPDF.criarTabela(header, getTodasOS());
        relatorioPDF.fecharDocumento();
    }

    public void pdfView(View viiew) {
        relatorioPDF.viewPDF();
    }

    private ArrayList<String[]> getTodasOS() {
        //OrdemServico os =  new OS_Controller(this).buscarTodasOrdemServicos();
        ArrayList <OrdemServico> os =  new OS_Controller(this).buscarTodasOrdemServicos();

        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<OrdemServico> ordemServicos=new ArrayList<>();

        for (OrdemServico ordemServico :os){
            String id = String.valueOf(ordemServico.getId());
            String numero = ordemServico.getNumero_ordem_servico();
            String cliente = ordemServico.getCliente().getNome();
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String dataEntrada = formato.format(ordemServico.getData_entrada());
            String status = ordemServico.getStatus_celular();
            String tecnico = ordemServico.getTecnico_responsavel();
            String precoFinal = ordemServico.getValor_final();
            String marca = ordemServico.getMarca();
            String modelo = ordemServico.getModelo();

            rows.add(new String[]{id,numero,dataEntrada, marca, modelo,cliente,status,tecnico, precoFinal});
            ordemServicos.add(ordemServico);
        }
        //String cliente = String.valueOf(os.getCliente());
        return rows;

    }

}