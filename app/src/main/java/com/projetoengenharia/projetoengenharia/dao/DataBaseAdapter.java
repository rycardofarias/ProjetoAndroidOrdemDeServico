package com.projetoengenharia.projetoengenharia.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ricardo Farias on 19/04/2018.
 */

public class DataBaseAdapter extends SQLiteOpenHelper {

    /**
     * classe que vai fazer todas as interações com o banco de dados
     */
    //dados do banco:
            //o cara botou protected
    private static final String NOME_BANCO = "dbMD.db";
    private static final int VERSION = 1;
    private static final String Tabela = "cliente";


    public DataBaseAdapter(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE endereco "+
                "( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "+
                "estado TEXT , "+
                "cidade TEXT , "+
                "bairro TEXT , "+
                "logadouro TEXT , "+
                "numero TEXT ) ";

        String sql2 = "CREATE TABLE cliente "+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT , "+
                "cpf TEXT UNIQUE , "+
                "data_nascimento DATE , "+
                "telefone TEXT UNIQUE , "+
                "email TEXT UNIQUE , "+
                "endereco_id INTEGER , "+
                "FOREIGN KEY(endereco_id) REFERENCES endereco(id))";

        String sql3 = "CREATE TABLE ordem_servico "+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "numero_ordem_servico INT UNIQUE , "+
                "status TEXT , "+
                "modelo TEXT , "+
                "marca TEXT , "+
                "data_entrada DATE , "+
                "data_previsao_saida DATE , "+
                "imei INTEGER UNIQUE , "+
                "acessorios TEXT , "+
                "detalhes TEXT , "+
                "defeito_reclamacao TEXT , "+
                "valor_previo DOUBLE , "+
                "valor_final DOUBLE , "+
                "tecnico_responsavel TEXT , "+
                "cliente_id INTEGER , "+
                "FOREIGN KEY(cliente_id) REFERENCES cliente(id))";

        db.execSQL(sql);
        db.execSQL(sql2);
        db.execSQL(sql3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS endereco";
        db.execSQL(sql);
        onCreate(db);
        String sql2 = "DROP TABLE IF EXISTS cliente";
        db.execSQL(sql2);
        onCreate(db);
        String sql3 = "DROP TABLE IF EXISTS ordem_servico";
        db.execSQL(sql3);
        onCreate(db);
    }
}
