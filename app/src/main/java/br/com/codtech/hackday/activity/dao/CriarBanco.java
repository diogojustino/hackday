package br.com.codtech.hackday.activity.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class CriarBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "calendario";
    public static final String ID = "id";
    public static final String DATA_INICIO = "data_inicio";
    public static final String DATA_FIM = "data_fim";

    private static final int VERSAO = 2;

    public CriarBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + DATA_INICIO + " text,"
                + DATA_FIM + " text" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion){
            db.execSQL("DROP TABLE IF exists " + TABELA);
            onCreate(db);
        }

    }
}