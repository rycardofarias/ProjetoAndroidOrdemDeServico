package com.projetoengenharia.projetoengenharia.pdf;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.projetoengenharia.projetoengenharia.activity.pdfActivity.ViewPdfActivity;
import com.projetoengenharia.projetoengenharia.activity.pdfActivity.ViewReciboActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReciboPDF {
    private Context context;
    private File pdfArquivo;
    private Document documento;
    private PdfWriter pdfEscreve;
    private Paragraph paragrafo;
    private Font fLetra =  new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.NORMAL);
    private Font fLetraNegrito =  new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD);
    private Font fHightTexto = new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.NORMAL, BaseColor.BLACK);

    public ReciboPDF(Context context) {
        this.context=context;
    }

    public void abriDocumentoRecibo(){
        criarRecibo();
        try{
            documento = new Document(PageSize.A5);
            pdfEscreve=PdfWriter.getInstance(documento, new FileOutputStream(pdfArquivo));
            documento.open();
        }catch (Exception e){
            Log.e("Abrir documento", e.toString());
        }
    }
    private void criarRecibo(){
        File pasta = new File(Environment.getExternalStorageDirectory().toString());
        if(!pasta.exists()){
            pasta.mkdirs();
        }
        Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dataConvertida = formato.format(date);
        pdfArquivo=new File(pasta, "ReciboOrdemServico"+dataConvertida+"PDF.pdf");

    }
    public void fecharReciboDocumento(){
        documento.close();
    }
    public void adicionarMetaDataRecibo(String titulo, String subject, String autor){
        documento.addTitle(titulo);
        documento.addSubject(subject);
        documento.addAuthor(autor);
    }
    //. . .. . . .. . . . .
    //
    //Eu coloquei tipo Date mas no Exemplo era String Lembrar
    //
    // . . . . ... . ..
    public void adicionarTitulosRecibo(String divisao, String nome_empresa,String titulo, String cliente, String marca, String modelo,
                                       String descricao, String defeito, String totalPagar, String tecnico, String divisao2) {
        try {
            paragrafo = new Paragraph();
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(nome_empresa, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(titulo, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(cliente, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(marca, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(modelo, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(descricao, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(defeito, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(tecnico, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoRecibo(new Paragraph(totalPagar, fLetraNegrito));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(nome_empresa, fLetra));
            adicionarParagafoPequenoCentralizadoRecibo(new Paragraph(divisao2, fLetra));
            //adicionarParagafoPequenoRecibo(new Paragraph("Gerado: " + data, fHightTexto));
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
        } catch (Exception e) {
            Log.e("adicionat titulos", e.toString());
        }
    }

    private  void adicionarParagafoPequenoCentralizadoRecibo(Paragraph pequenoParagrafo){
        pequenoParagrafo.setAlignment(Element.ALIGN_CENTER);
        paragrafo.add(pequenoParagrafo);
    }
    private  void adicionarParagafoPequenoRecibo(Paragraph pequenoParagrafo){
        pequenoParagrafo.setAlignment(Element.ALIGN_BASELINE);
        paragrafo.add(pequenoParagrafo);
    }
    public void addParagrafoRecibo(String texto){
        try{
            paragrafo=new Paragraph(texto, fLetra);
            paragrafo.setSpacingAfter(3);
            paragrafo.setSpacingBefore(3);
            documento.add(paragrafo);
        }catch (Exception e){
            Log.e("adicionar paragrafo", e.toString());
        }
    }

    public void viewReciboPDF(){
        Intent intent = new Intent(context, ViewReciboActivity.class);
        intent.putExtra("path",pdfArquivo.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}


