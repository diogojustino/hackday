package br.com.codtech.hackday.activity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.codtech.hackday.activity.model.Calendario;

public class BancoController {

    private SQLiteDatabase db;
    private CriarBanco banco;

    public BancoController(Context context){
        banco = new CriarBanco(context);
    }

    public String insereDado(String dataInicio, String dataFim){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriarBanco.DATA_INICIO, dataInicio);
        valores.put(CriarBanco.DATA_FIM, dataFim);


        resultado = db.insert(CriarBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            throw new RuntimeException("Erro ao inserir registro");
        else
            throw new RuntimeException("Registro inserido com sucesso");

    }

    public List<Calendario> carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.DATA_INICIO,banco.DATA_FIM};
        db = banco.getReadableDatabase();
        List<Calendario> calendarios = null;
        try{
            cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
                calendarios = new ArrayList<>();

                while(cursor.moveToNext()){
                    Long id = cursor.getLong(cursor.getColumnIndexOrThrow(CriarBanco.ID));
                    String dataInicio = cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.DATA_INICIO));
                    String dataFim= cursor.getString(cursor.getColumnIndexOrThrow(CriarBanco.DATA_FIM));
                    calendarios.add(new Calendario(id, dataInicio, dataFim));
                }
            }
        }catch(Exception exception){

        }

        db.close();
        return calendarios;
    }
}
