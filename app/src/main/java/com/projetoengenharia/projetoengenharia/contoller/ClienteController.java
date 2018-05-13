package com.projetoengenharia.projetoengenharia.contoller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projetoengenharia.projetoengenharia.dao.DataBaseAdapter;
import com.projetoengenharia.projetoengenharia.model.Cliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo Farias on 19/04/2018.
 */

public class ClienteController extends DataBaseAdapter{

    public ClienteController(Context context){
        super(context);
        //integração com o banco
    }


    public boolean create(Cliente cliente){
        boolean verifica=false;
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            //pegando os dados
            ContentValues values_cliente = new ContentValues();
            ContentValues values_endereco = new ContentValues();
            values_endereco.put("estado", cliente.getEndereco().getEstado());
            values_endereco.put("cidade", cliente.getEndereco().getCidade());
            values_endereco.put("bairro", cliente.getEndereco().getBairro());
            values_endereco.put("logadouro", cliente.getEndereco().getLogadouro());
            values_endereco.put("numero", cliente.getEndereco().getNumero());
            long id_endereco = db.insert("endereco", null, values_endereco);
            System.out.println("Imprimindo id_cliente"+id_endereco);
            values_cliente.put("nome", cliente.getNome());
            values_cliente.put("cpf", cliente.getCPF());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            values_cliente.put("data_nascimento", dateFormat.format(cliente.getDataNascimento()));
            values_cliente.put("telefone", cliente.getTelefone());
            values_cliente.put("email", cliente.getEmail());
            values_cliente.put("endereco_id", id_endereco);
            long id_cliente = db.insert("cliente", null, values_cliente);
            if(id_cliente==-1){
                verifica=false;
            } else {
                db.setTransactionSuccessful();
                verifica = true;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }finally {
            db.endTransaction();
        }
        db.close();
        return  verifica;
    }

    public int totalclientes(){

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM cliente ";
        int contador = db.rawQuery(sql, null).getCount();

        return contador;
    }

    public List<Cliente> listCliente(){
        List<Cliente> clientes= new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER by nome";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));

                Cliente cliente = new Cliente();
                cliente.setId(id);
                cliente.setNome(nome);

                clientes.add(cliente);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clientes;
    }

    public Cliente buscarPeloId(int Cliente){
        return null;
    }
    public boolean updateCliente (Cliente cliente){
        return true;
    }

    public boolean deleteCliente(int clienteId){
        return true;
    }

}
