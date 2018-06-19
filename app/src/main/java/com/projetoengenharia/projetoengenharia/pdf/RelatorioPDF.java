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

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class RelatorioPDF {
    private Context context;
    private  File pdfArquivo;
    private Document documento;
    private PdfWriter pdfEscreve;
    private Paragraph paragrafo;
    private Font fTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
    private Font fSubtitulo = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private Font fTexto = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
    private Font fHightTexto = new Font(Font.FontFamily.TIMES_ROMAN, 15,Font.BOLD, BaseColor.RED);

    public RelatorioPDF(Context context) {
        this.context=context;
    }
    public void abriDocumento(){
        criarArquivo();
        try{
            documento = new Document(PageSize.A4);
            pdfEscreve=PdfWriter.getInstance(documento, new FileOutputStream(pdfArquivo));
            documento.open();
        }catch (Exception e){
            Log.e("Abrir documento", e.toString());
        }
    }
    private void criarArquivo(){
        File pasta = new File(Environment.getExternalStorageDirectory().toString());
        if(!pasta.exists()){
            pasta.mkdirs();
        }
        pdfArquivo=new File(pasta, "RelatorioOrdemServicoPDF.pdf");

    }
    public void fecharDocumento(){
        documento.close();
    }
    public void adicionarMetaData(String titulo, String subject, String autor){
        documento.addTitle(titulo);
        documento.addSubject(subject);
        documento.addAuthor(autor);
    }
    //. . .. . . .. . . . .
    //
    //Eu coloquei tipo Date mas no Exemplo era String Lembrar
    //
    // . . . . ... . ..
    public void adicionarTitulos(String titulo, String subTitulo, String data) {
        try {
            paragrafo = new Paragraph();
            adicionarParagafoPequenoCentralizadoo(new Paragraph(titulo, fTitulo));
            adicionarParagafoPequeno(new Paragraph(subTitulo, fSubtitulo));
            adicionarParagafoPequeno(new Paragraph("Gerado: " + data, fHightTexto));
            paragrafo.setSpacingAfter(30);
            documento.add(paragrafo);
        } catch (Exception e) {
            Log.e("adicionat titulos", e.toString());
        }
    }

    private  void adicionarParagafoPequeno(Paragraph pequenoParagrafo){
        pequenoParagrafo.setAlignment(Element.ALIGN_BASELINE);
        paragrafo.add(pequenoParagrafo);
    }
    private  void adicionarParagafoPequenoCentralizadoo(Paragraph pequenoParagrafo){
        pequenoParagrafo.setAlignment(Element.ALIGN_CENTER);
        paragrafo.add(pequenoParagrafo);
    }
    public void addParagrafo(String texto){
        try{
            paragrafo=new Paragraph(texto, fTexto);
            paragrafo.setSpacingAfter(5);
            paragrafo.setSpacingBefore(5);
            documento.add(paragrafo);
        }catch (Exception e){
            Log.e("adicionar paragrafo", e.toString());
        }
    }
    public void criarTabela(String[]header, ArrayList<String[]>clientes){
        try{
        paragrafo=new Paragraph();
        paragrafo.setFont(fTexto);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(100);
        PdfPCell pdfPCell;
        int indexC=0;
        while(indexC<header.length){
            pdfPCell=new PdfPCell(new Phrase(header[indexC++],fSubtitulo));
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPCell.setBackgroundColor(BaseColor.GRAY);
            pdfPTable.addCell(pdfPCell);
        }
        for (int indexR=0; indexR<clientes.size(); indexR++){
            String[]row=clientes.get(indexR);
            for (indexC=0;indexC<header.length; indexC++){
                pdfPCell=new PdfPCell(new Phrase(row[indexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(30);
                pdfPTable.addCell(pdfPCell);

            }
        }
        paragrafo.add(pdfPTable);
        documento.add(paragrafo);
        }catch (Exception e) {
            Log.e("criar documento", e.toString());
        }
    }
    public void viewPDF(){
        Intent intent = new Intent(context, ViewPdfActivity.class);
        intent.putExtra("path",pdfArquivo.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

