package com.projetoengenharia.projetoengenharia.contoller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projetoengenharia.projetoengenharia.dao.DataBaseAdapter;
import com.projetoengenharia.projetoengenharia.model.OrdemServico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ricardo Farias on 30/04/2018.
 */

public class OS_Controller extends DataBaseAdapter {
    public OS_Controller(Context context) {
        super(context);
    }

    public boolean create(OrdemServico ordemServico){
        boolean verifica=false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();

            values.put("numero_ordem_servico", ordemServico.getNumero_ordem_servico());
            values.put("status", ordemServico.getStatus_celular());
            values.put("modelo", ordemServico.getModelo());
            values.put("marca", ordemServico.getMarca());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            values.put("data_entrada", dateFormat.format(ordemServico.getData_entrada()));
            //values.put("data_saida",dateFormat.format(ordemServico.getPrevisao_saida()));

            values.put("imei", ordemServico.getIMEI());
            values.put("acessorios", ordemServico.getAcessorios());
            values.put("detalhes", ordemServico.getDetalhes());
            values.put("defeito_reclamacao", ordemServico.getDefeito_reclamacao());
            values.put("valor_previo", ordemServico.getValor_previo());
            values.put("valor_final", ordemServico.getValor_final());
            values.put("tecnico_responsavel", ordemServico.getTecnico_responsavel());
            values.put("cliente_id", ordemServico.getCliente().getId());

            long os =db.insert("ordem_servico", null, values);
            System.out.println(os);
            if(os==-1){
                System.out.println("Deu pau");
                verifica=false;
            } else {
                db.setTransactionSuccessful();
                verifica = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
        db.close();
        return  verifica;
    }
    public List<OrdemServico> listOS(){
        List<OrdemServico> ordemServicos= new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico ORDER by marca";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                int numero_ordem_servico = cursor.getInt(cursor.getColumnIndex("numero_ordem_servico"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
                String marca = cursor.getString(cursor.getColumnIndex("marca"));
                String data_entrada = cursor.getString(cursor.getColumnIndex("data_entrada"));
                Double valor_previo = cursor.getDouble(cursor.getColumnIndex("valor_previo"));
                //int cliente_id = cursor.getInt(cursor.getColumnIndex("cliente_id"));
                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(id);
                ordemServico.setNumero_ordem_servico(numero_ordem_servico);
                ordemServico.setStatus_celular(status);
                ordemServico.setModelo(modelo);
                ordemServico.setMarca(marca);

                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date dataConvertida = formato.parse(data_entrada);
                    ordemServico.setData_entrada(dataConvertida);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ordemServico.setValor_previo(valor_previo);
                ordemServicos.add(ordemServico);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ordemServicos;
    }
}
