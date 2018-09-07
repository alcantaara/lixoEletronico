package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.lixoeletronico.lixoeletronico.adapter.ListaAdapter;
import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UsuarioDAO dao = new UsuarioDAO(this);
        Intent i = getIntent();
        String id = i.getStringExtra("id");
        List<Agenda> agendas = dao.buscaAgendamentos(id);
        dao.close();

        Log.d("mostraid",id);
        /*Log.d("numeroagenda", agendas.get(1).getData());
        int i = 0;
        while(!agendas.isEmpty()){
            Log.d("numeroagenda", agendas.get(i).getData());
            i++;
        }*/

        ListView lista = (ListView) findViewById(R.id.perfil_lista);


        /*
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_1, agendas);
        lista.setAdapter(adapter);*/

        ListaAdapter adapter = new ListaAdapter(this,agendas);
        lista.setAdapter(adapter);


    }


}
