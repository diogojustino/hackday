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
import br.com.codtech.hackday.activity.model.Calendario;

public class AdicionarCalendarioActivity extends ModeloActivity {
    private Button buttonSalvar;
    private EditText editDataInicio;
    private EditText editDataFim;
    private Calendario calendario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_calendario);
        buttonSalvar = findViewById(R.id.button_salvar);
        editDataInicio = findViewById(R.id.edit_data_inicio);
        editDataFim = findViewById(R.id.edit_data_fim);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            calendario = (Calendario) bundle.getSerializable("instance");
            editDataInicio.setText(calendario.getDataInicio());
            editDataFim.setText(calendario.getDataFim());
        }
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
                if(calendario == null) {

                    try {
                        crud.insereDado(dataInicio, dataFim);
                    } catch (RuntimeException runtimeException) {
                        alerta(runtimeException.getMessage(), Toast.LENGTH_SHORT);
                    }
                }else{

                    crud.alteraRegistro(calendario.getId(), dataInicio, dataFim);
                    alerta("Atualizado com sucesso", Toast.LENGTH_SHORT);
                }
                finish();
            }
        });
    }
}
