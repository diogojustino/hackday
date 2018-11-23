package br.com.codtech.hackday.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class ModeloActivity extends AppCompatActivity {

    public void alerta(String mensagem, int tempoDuracao){
        Toast.makeText(getBaseContext(), mensagem, tempoDuracao).show();
    }
}
