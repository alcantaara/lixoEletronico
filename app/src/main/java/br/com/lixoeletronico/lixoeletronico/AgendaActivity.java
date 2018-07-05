package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;


public class AgendaActivity extends AppCompatActivity {

    private String data;
    private AgendarHelper helper;
    String nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CalendarView calendarView = (CalendarView) findViewById(R.id.agenda_calendario);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int dia) {
                data = dia +"/"+ mes + "/" + ano;

            }
        });

        final Spinner horario = (Spinner) findViewById(R.id.spHorario);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.horario,R.layout.support_simple_spinner_dropdown_item);
        horario.setAdapter(adapter1);


        horario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] some_array = getResources().getStringArray(R.array.horario);
                 nome =  some_array[position]  ;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




            Button continuar = (Button) findViewById(R.id.agenda_continuar);
        continuar.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){

                    //helper = new AgendarHelper(data,nome);
                    Intent i = getIntent();
                    String endereco = i.getStringExtra("endereco");
                    String numero = i.getStringExtra("numero");
                    String bairro = i.getStringExtra("bairro");
                    String complemento = i.getStringExtra("complemeto");
                    String id = i.getStringExtra("id");
                    Intent irContinuar = new Intent(AgendaActivity.this, AparelhosActivity.class);
                    irContinuar.putExtra("endereco", endereco);
                    irContinuar.putExtra("numero", numero);
                    irContinuar.putExtra("bairro", bairro);
                    irContinuar.putExtra("complemeto", complemento);
                    irContinuar.putExtra("data", data);
                    irContinuar.putExtra("hora", nome);
                    irContinuar.putExtra("id", id);
                    Log.d("mostraid", "agenda" + id);
                    startActivity(irContinuar);
            }
            });
        }

}
