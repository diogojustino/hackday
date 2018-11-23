package br.com.codtech.hackday.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import br.com.codtech.hackday.R;
import br.com.codtech.hackday.activity.adapter.ListCalendarioAdapter;
import br.com.codtech.hackday.activity.dao.BancoController;

public class AdicionarCalendarioActivity extends ModeloActivity {
    private Button buttonSalvar;
    private EditText editDataInicio;
    private EditText editDataFim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_calendario);
        buttonSalvar = findViewById(R.id.button_salvar);
        editDataInicio = findViewById(R.id.edit_data_inicio);
        editDataFim = findViewById(R.id.edit_data_fim);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataInicio = editDataInicio.getText().toString();
                String dataFim = editDataFim.getText().toString();
                if(dataInicio.isEmpty()){
                    alerta("Data De Inicio esta Vazia", Toast.LENGTH_LONG);
                    return;
                }
                if(dataInicio.isEmpty()){
                    alerta("Data De Inicio esta Vazia", Toast.LENGTH_LONG);
                    return;
                }

                BancoController crud = new BancoController(getBaseContext());
                try{
                    crud.insereDado(dataInicio, dataFim);
                }catch (RuntimeException runtimeException) {
                    alerta(runtimeException.getMessage(), Toast.LENGTH_SHORT);

                    finish();
                }

            }
        });
    }
}
