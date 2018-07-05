package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import android.widget.GridLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.attr.id;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public String id_aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GridLayout menu_grid = (GridLayout) findViewById(R.id.menu_grid);

        SetSingleEvent(menu_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* UsuarioDAO dao = new UsuarioDAO(MenuActivity.this);
        Usuario usuario = new Usuario();
        id = dao.buscaID(usuario.getEmail());
        dao.close();*/

        //Realizada a chamada do menu lateral

        /*Intent j = getIntent();
        final String email = j.getStringExtra("email");
        final String senha = j.getStringExtra("senha");*/
        Usuario usuario = new Usuario();
        pegaid(usuario.getEmail(),usuario.getSenha());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void SetSingleEvent(GridLayout menu_grid) {
        for (int i = 0; i < menu_grid.getChildCount(); i++) {

            CardView cardView = (CardView) menu_grid.getChildAt(i);
            final int finalI = i;
            if (i == 0) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Log.d("mostraid", id_aux);

                        Intent verPerfil = new Intent(MenuActivity.this, PerfilActivity.class);
                        verPerfil.putExtra("id", id_aux);
                        startActivity(verPerfil);

                    }
                });
            }
            if (i == 1) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent verPerfil = new Intent(MenuActivity.this, LocaisActivity.class);
                        startActivity(verPerfil);

                    }
                });
            }
            if (i == 2){
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent verPerfil = new Intent(MenuActivity.this, EnderecoActivity.class);
                        verPerfil.putExtra("id", id_aux);
                        startActivity(verPerfil);

                    }
                });
            }
            if (i == 3){
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent verPerfil = new Intent(MenuActivity.this, MoedasActivity.class);
                        startActivity(verPerfil);

                    }
                });
            }


        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_lixo) {
            Intent lixo = new Intent(MenuActivity.this, LixoActivity.class);
            startActivity(lixo);

        }else if (id == R.id.nav_informacoes) {
            Intent informacao = new Intent(MenuActivity.this,InformacoesActivity.class);
            startActivity(informacao);

        } else if (id == R.id.nav_gallery) {
            Intent fale = new Intent(MenuActivity.this,FaleConoscoActivity.class);
            startActivity(fale);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            finishAffinity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void pegaid(String email, String senha){
        //Here we will handle the http request to insert user to mysql db
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        BancoIP ip = new BancoIP();
        final String ROOT_URL  = ip.getIp();
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegistroAPI api = adapter.create(RegistroAPI.class);

        //Defining the method insertuser of our interface
        api.buscaid(

                //Passing the values by getting it from editTexts
                email,
                senha,

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(output);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            final String id = obj.getString("id");
                            id_aux = id;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(MenuActivity.this, id_aux,Toast.LENGTH_LONG).show();

                    }



                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );


    }
}
