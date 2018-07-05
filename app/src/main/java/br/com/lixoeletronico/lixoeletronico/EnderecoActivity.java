package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class EnderecoActivity extends AppCompatActivity {

    private AgendarHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final EditText endereco = (EditText) findViewById(R.id.agenda_endereco);
        final EditText numero = (EditText) findViewById(R.id.agenda_numero);
        final EditText complemento = (EditText) findViewById(R.id.agenda_complemento);
        final EditText bairro = (EditText) findViewById(R.id.agenda_bairro);

        /*helper = new AgendarHelper(endereco, numero, complemento, bairro);*/


        Spinner estado = (Spinner) findViewById(R.id.spEstado);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.estado, R.layout.support_simple_spinner_dropdown_item);
        estado.setAdapter(adapter);

        Spinner cidade = (Spinner) findViewById(R.id.spCidade);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.cidade, R.layout.support_simple_spinner_dropdown_item);
        cidade.setAdapter(adapter1);


        Button seguir = (Button) findViewById(R.id.agenda_seguir);

        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent j = getIntent();
                String id = j.getStringExtra("id");
                Intent irSeguir = new Intent(EnderecoActivity.this, AgendaActivity.class);
                irSeguir.putExtra("endereco", endereco.getText().toString());
                irSeguir.putExtra("numero", numero.getText().toString());
                irSeguir.putExtra("bairro", bairro.getText().toString());
                irSeguir.putExtra("complemeto", complemento.getText().toString());
                irSeguir.putExtra("id", id);
                Log.d("mostraid", "endereco " + id);
                startActivity(irSeguir);
            }
        });


    }
}