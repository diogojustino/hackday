package br.com.codtech.hackday.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import br.com.codtech.hackday.R;
import br.com.codtech.hackday.activity.adapter.ListCalendarioAdapter;

public class MainActivity extends ModeloActivity {

    private ToggleButton toggleOnOff;
    private ImageButton imageAdicionarHorario;
    private ListView listCalendario;
    private ListCalendarioAdapter listCalendarioAdapter;
    private SwipeRefreshLayout swipeRefresh;

    public SwipeRefreshLayout getSwipeRefresh(){
        return swipeRefresh;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toggleOnOff = findViewById(R.id.toggle_on_off);
        imageAdicionarHorario = findViewById(R.id.image_adicionar_horario);
        listCalendario = findViewById(R.id.list_calendario);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        listCalendarioAdapter = new ListCalendarioAdapter(this);
        listCalendario.setAdapter(listCalendarioAdapter);

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        inflater.inflate(R.layout.calendario_row, null);


        toggleOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Ligar luz
                    alerta("Sistema Ligado", Toast.LENGTH_SHORT);
                } else {
                    //desligar
                    alerta("Sistema desligado", Toast.LENGTH_SHORT);
                }
            }
        });

        imageAdicionarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AdicionarCalendarioActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        listCalendarioAdapter.dataUpdate();
    }
}
