package com.projetoengenharia.projetoengenharia.contoller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projetoengenharia.projetoengenharia.dao.DataBaseAdapter;
import com.projetoengenharia.projetoengenharia.model.Cliente;
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

            values.put("imei", ordemServico.getIMEI());
            values.put("acessorios", ordemServico.getAcessorios());
            values.put("detalhes", ordemServico.getDetalhes());
            values.put("defeito_reclamacao", ordemServico.getDefeito_reclamacao());
            values.put("valor_previo", ordemServico.getValor_previo());
            values.put("valor_final", ordemServico.getValor_final());
            values.put("tecnico_responsavel", ordemServico.getTecnico_responsavel());
            values.put("cliente_id", ordemServico.getCliente().getId());

            long os =db.insert("ordem_servico", null, values);
            if(os==-1){
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
    public boolean updateOS (OrdemServico ordemServico){
        ContentValues values = new ContentValues();

        values.put("status", ordemServico.getStatus_celular());
        values.put("valor_final", ordemServico.getValor_final());

        String where = "id = ? ";
        String[] whereArgs = {Integer.toString(ordemServico.getId())};

        SQLiteDatabase db = this.getWritableDatabase();
        boolean isUpdateOS = db.update("ordem_servico", values, where, whereArgs)>0;

        return true;
    }

    public List<OrdemServico> listOS(){
        List<OrdemServico> ordemServicos= new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico ORDER by status";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String numero_ordem_servico = cursor.getString(cursor.getColumnIndex("numero_ordem_servico"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                String modelo = cursor.getString(cursor.getColumnIndex("modelo"));
                String marca = cursor.getString(cursor.getColumnIndex("marca"));
                String data_entrada = cursor.getString(cursor.getColumnIndex("data_entrada"));
                String valor_final = cursor.getString(cursor.getColumnIndex("valor_final"));
                int cliente_id = cursor.getInt(cursor.getColumnIndex("cliente_id"));
                //System.out.println("Cliente id"+ cliente_id);
                OrdemServico ordemServico = new OrdemServico();
                ordemServico.setId(id);
                ordemServico.setNumero_ordem_servico(numero_ordem_servico);
                ordemServico.setStatus_celular(status);
                ordemServico.setModelo(modelo);
                ordemServico.setValor_final(valor_final);
                ordemServico.setMarca(marca);

                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date dataConvertida = formato.parse(data_entrada);
                    ordemServico.setData_entrada(dataConvertida);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Cliente cliente = new Cliente();
                String sql2 = "SELECT * FROM cliente WHERE id = "+cliente_id;
                Cursor cursor2 = db.rawQuery(sql2, null);
                if(cursor2.moveToFirst()){
                    String nome = cursor2.getString(cursor2.getColumnIndex("nome"));
                    String email = cursor2.getString(cursor2.getColumnIndex("email"));

                    cliente.setId(Integer.valueOf(cliente_id));
                    cliente.setNome(nome);
                    cliente.setEmail(email);
                }
                ordemServico.setCliente(cliente);
                ordemServicos.add(ordemServico);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ordemServicos;
    }
    public OrdemServico buscapeloId(int osID){
        OrdemServico ordemServico = null;
        Cliente cliente = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM ordem_servico WHERE id = "+osID;
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){

            String numero = cursor.getString(cursor.getColumnIndex("numero_ordem_servico"));
            String status= cursor.getString(cursor.getColumnIndex("status"));
            String valorFinal= cursor.getString(cursor.getColumnIndex("valor_final"));
            String modelo= cursor.getString(cursor.getColumnIndex("modelo"));
            String marca= cursor.getString(cursor.getColumnIndex("marca"));
            String data_entrada= cursor.getString(cursor.getColumnIndex("data_entrada"));
            String imei= cursor.getString(cursor.getColumnIndex("imei"));
            String acessorios= cursor.getString(cursor.getColumnIndex("acessorios"));
            String detalhes= cursor.getString(cursor.getColumnIndex("detalhes"));
            String defeito= cursor.getString(cursor.getColumnIndex("defeito_reclamacao"));
            String valorPrevio= cursor.getString(cursor.getColumnIndex("valor_previo"));
            String tecnico= cursor.getString(cursor.getColumnIndex("tecnico_responsavel"));
            String cliente_os = cursor.getString(cursor.getColumnIndex("cliente_id"));
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));

            ordemServico = new OrdemServico();
            ordemServico.setNumero_ordem_servico(numero);
            ordemServico.setStatus_celular(status);
            ordemServico.setModelo(modelo);
            ordemServico.setMarca(marca);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                ordemServico.setData_entrada(dateFormat.parse(data_entrada));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ordemServico.setIMEI(imei);
            ordemServico.setAcessorios(acessorios);
            ordemServico.setDetalhes(detalhes);
            ordemServico.setDefeito_reclamacao(defeito);
            ordemServico.setValor_previo(valorPrevio);
            ordemServico.setValor_final(valorFinal);
            ordemServico.setTecnico_responsavel(tecnico);

            cliente = new Cliente();
            String sql2 = "SELECT * FROM cliente WHERE id = "+cliente_os;
            Cursor cursor2 = db.rawQuery(sql2, null);
            if(cursor2.moveToFirst()){
                String nome = cursor2.getString(cursor2.getColumnIndex("nome"));
                String email = cursor2.getString(cursor2.getColumnIndex("email"));

                cliente.setId(Integer.valueOf(cliente_os));
                cliente.setNome(nome);
                cliente.setEmail(email);
            }
            ordemServico.setCliente(cliente);
            ordemServico.setId(id);
        }
        return ordemServico;
    }
}
