package com.projetoengenharia.projetoengenharia.contoller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.projetoengenharia.projetoengenharia.dao.DataBaseAdapter;
import com.projetoengenharia.projetoengenharia.model.Cliente;
import com.projetoengenharia.projetoengenharia.model.Endereco;

import java.text.ParseException;
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
    public boolean updateCliente (Cliente cliente){

        ContentValues values = new  ContentValues();
        ContentValues valuesEndereco =  new ContentValues();

        valuesEndereco.put("estado", cliente.getEndereco().getEstado());
        valuesEndereco.put("cidade", cliente.getEndereco().getCidade());
        valuesEndereco.put("bairro", cliente.getEndereco().getBairro());
        valuesEndereco.put("logadouro", cliente.getEndereco().getLogadouro());
        valuesEndereco.put("numero", cliente.getEndereco().getNumero());

        String where = "id = ? ";
        String[] whereArgs = {Integer.toString(cliente.getEndereco().getId())};

        SQLiteDatabase db = this.getWritableDatabase();
        boolean isUpdateEndereco = db.update("endereco", valuesEndereco, where, whereArgs)>0;

        values.put("nome" , cliente.getNome());
        values.put("cpf" , cliente.getCPF());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        values.put("data_nascimento", dateFormat.format(cliente.getDataNascimento()));
        values.put("telefone" , cliente.getTelefone());
        values.put("email" , cliente.getEmail());
        //values.put("endereco_id" , cliente.getEndereco().getId());

        String where2 = "id = ? ";
        String[] whereArgs2 = {Integer.toString(cliente.getId())};

        boolean isUpdateCliente = db.update("cliente", values, where2, whereArgs2)>0;

        return true;
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

    public Cliente buscarPeloId(int clienteID){
        Cliente cliente = null;
        Endereco endereco = null;
        SQLiteDatabase db =this.getReadableDatabase();
        String sql = "SELECT * FROM cliente WHERE id = "+clienteID;
        Cursor cursor2 = db.rawQuery(sql, null);
        if (cursor2.moveToFirst()){

            endereco = new Endereco();
            String nome= cursor2.getString(cursor2.getColumnIndex("nome"));
            String cpf= cursor2.getString(cursor2.getColumnIndex("cpf"));
            String dataNascimento= cursor2.getString(cursor2.getColumnIndex("data_nascimento"));
            String telefone= cursor2.getString(cursor2.getColumnIndex("telefone"));
            String email= cursor2.getString(cursor2.getColumnIndex("email"));
            String endereco_cliente= cursor2.getString(cursor2.getColumnIndex("endereco_id"));

            cliente = new Cliente();
            cliente.setId(clienteID);
            cliente.setNome(nome);
            cliente.setCPF(cpf);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                cliente.setDataNascimento(dateFormat.parse(dataNascimento));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cliente.setTelefone(telefone);
            cliente.setEmail(email);


            String sql2  = "SELECT * FROM endereco WHERE id = " + endereco_cliente;
            Cursor cursor = db.rawQuery(sql2, null);
            if (cursor.moveToFirst()) {
                String estado = cursor.getString(cursor.getColumnIndex("estado"));
                String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
                String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
                String rua = cursor.getString(cursor.getColumnIndex("logadouro"));
                String numero = cursor.getString(cursor.getColumnIndex("numero"));

                endereco.setId(Integer.valueOf(endereco_cliente));
                endereco.setEstado(estado);
                endereco.setCidade(cidade);
                endereco.setBairro(bairro);
                endereco.setLogadouro(rua);
                endereco.setNumero(numero);

                cliente.setEndereco(endereco);
            }

        }
        return cliente;
    }

    public boolean deleteCliente(int clienteId){

        boolean isDeletadoCliente = false;
        boolean isDeletadoEndereco = false;

        SQLiteDatabase db=  this.getWritableDatabase();
        isDeletadoCliente = db.delete("cliente", "id ='" +clienteId + "'", null) >0;
        isDeletadoEndereco = db.delete("endereco", "id ='" +clienteId + "'", null) >0;

        db.close();
        return true;
    }

}
