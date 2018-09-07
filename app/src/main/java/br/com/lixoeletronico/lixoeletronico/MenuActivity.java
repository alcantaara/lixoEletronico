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

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        GridLayout menu_grid = (GridLayout) findViewById(R.id.menu_grid);

        SetSingleEvent(menu_grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UsuarioDAO dao = new UsuarioDAO(MenuActivity.this);
        Usuario usuario = new Usuario();
        id = dao.buscaID(usuario.getEmail());
        dao.close();


        //Realizada a chamada do menu lateral

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

                        Log.d("mostraid", id);

                        Intent verPerfil = new Intent(MenuActivity.this, PerfilActivity.class);
                        verPerfil.putExtra("id", id);
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

                        Log.d("mostraid", id);

                        Intent verPerfil = new Intent(MenuActivity.this, EnderecoActivity.class);
                        verPerfil.putExtra("id", id);
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
}
