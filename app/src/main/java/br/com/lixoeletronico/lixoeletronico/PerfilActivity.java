package br.com.lixoeletronico.lixoeletronico;

import android.content.Context;
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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.lixoeletronico.lixoeletronico.adapter.ListaAdapter;
import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PerfilActivity extends AppCompatActivity {

    public List<Agenda> agendas;
    public Agenda agenda;
    public String id, quantidade;
    public ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // UsuarioDAO dao = new UsuarioDAO(this);
        Intent i = getIntent();
        id = i.getStringExtra("id");
        //agendas = dao.buscaAgendamentos(id);
       // dao.close();

        Log.d("mostraid",id);
        /*Log.d("numeroagenda", agendas.get(1).getData());
        int i = 0;
        while(!agendas.isEmpty()){
            Log.d("numeroagenda", agendas.get(i).getData());
            i++;
        }*/

        lista = (ListView) findViewById(R.id.perfil_lista);


        /*
        ArrayAdapter<Agenda> adapter = new ArrayAdapter<Agenda>(this, android.R.layout.simple_list_item_1, agendas);
        lista.setAdapter(adapter);*/
        dadosagenda(this);
    }

    public void dadosagenda(final Context context){

        BancoIP ip = new BancoIP();
        final String ROOT_URL  = ip.getIp();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegistroAPI api = adapter.create(RegistroAPI.class);
        agenda = new Agenda();
        agendas = new ArrayList<Agenda>();

        //Defining the method insertuser of our interface
        api.dadoscoleta(
                id,
                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result , Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));



                            //Reading the output in the string
                            output = reader.readLine();
                            agendas = getAgenda(output);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ListaAdapter adapter = new ListaAdapter(context,agendas);
                        lista.setAdapter(adapter);
                       /* JSONObject obj = null;
                        try {
                            obj = new JSONObject(output);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            agenda.setSubCategoria(obj.getString("subcategoria"));
                            agenda.setQuantidade(obj.getString("quantidade"));
                            agenda.setData(obj.getString("data"));
                            agenda.setFoto(obj.getString("foto"));
                            agendas.add(agenda);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                        //Toast.makeText(MenuActivity.this, id_aux,Toast.LENGTH_LONG).show();

                    }



                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );


    }

    private List<Agenda> getAgenda(String jsonString) {

        List<Agenda> agendas = new ArrayList<Agenda>();
        try {

            JSONArray trendLists = new JSONArray(jsonString);
            JSONObject trendList;
           // JSONArray trendsArray = trendList.getJSONArray(0);

            for (int i = 0; i < trendLists.length(); i++) {
              //  trend = new JSONObject(trendList);
                trendList = trendLists.getJSONObject(i);
                Agenda agenda = new Agenda();
                Log.i("DEVMEDIA", "foto=" + trendList.getString("foto"));
                Log.i("DEVMEDIA", "quantidade=" + trendList.getString("quantidade"));
                Log.i("DEVMEDIA", "subcategoria=" + trendList.getString("subcategoria"));
                Log.i("DEVMEDIA", "data=" + trendList.getString("data"));

                agenda.setFoto(trendList.getString("foto"));
                agenda.setQuantidade(trendList.getString("quantidade"));
                agenda.setSubCategoria(trendList.getString("subcategoria"));
                agenda.setData(trendList.getString("data"));

                agendas.add(agenda);
            }
            Log.i("DEVMEDIA", "tamanho da lista do Json =" + agendas.size());
        } catch (JSONException e) {
            Log.e("DEVMEDIA", "Erro no parsing do JSON", e);
        }

        return agendas;
    }


}
