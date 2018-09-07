package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;

public class AparelhosActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    public static final int REQUEST_CODE = 1;
    private Spinner spCategoria;
    private Spinner spSubCategoria;
    private String caminhoFoto, subCategoria, qtd = null;
    private AgendarHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparelhos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spSubCategoria = (Spinner) findViewById(R.id.spSubCategoria);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categoria,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategoria.setAdapter(adapter);
        spCategoria.setOnItemSelectedListener(this);

            spSubCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String some_array = spSubCategoria.getItemAtPosition(position).toString();
                    subCategoria = (some_array);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        ImageButton botaoFoto = (ImageButton) findViewById(R.id.botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null)+"/"+ System.currentTimeMillis() + "jpg";
                File arquivoFoto = new File(caminhoFoto);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(AparelhosActivity.this, BuildConfig.APPLICATION_ID +".provider", arquivoFoto));
                startActivityForResult(intentCamera, REQUEST_CODE);
            }
        });

        Button botaoOutro = (Button) findViewById(R.id.botao_outro);
        botaoOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent outroAp = new Intent(AparelhosActivity.this, AparelhosActivity.class);
                startActivity(outroAp);

                Toast.makeText(AparelhosActivity.this,"Agedamento Salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button botaoConcluir = (Button) findViewById(R.id.botao_agendou);
        botaoConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = getIntent();
                String endereco = i.getStringExtra("endereco");
                String numero = i.getStringExtra("numero");
                String bairro = i.getStringExtra("bairro");
                String complemento = i.getStringExtra("complemeto");
                String data = i.getStringExtra("data");
                String hora = i.getStringExtra("hora");
                String id = i.getStringExtra("id");
                Integer id_usuario = Integer.parseInt(id);
                Integer numerocasa = Integer.parseInt(numero);


                final EditText quantidade = (EditText) findViewById(R.id.agenda_quantidade);
                qtd = quantidade.getText().toString();

                helper = new AgendarHelper(id_usuario,endereco,numerocasa,complemento,bairro,data,hora,subCategoria,caminhoFoto,qtd);
                Agenda agenda = helper.pegaAgenda();
                agenda.Imprime();
                UsuarioDAO usuarioDAO = new UsuarioDAO(AparelhosActivity.this);
                usuarioDAO.insereAgendar(agenda);
                usuarioDAO.close();

                Intent agendou = new Intent(AparelhosActivity.this, MenuActivity.class);
                startActivity(agendou);

                Toast.makeText(AparelhosActivity.this,"Agedamento Salvo!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==  REQUEST_CODE && resultCode == AparelhosActivity.RESULT_OK){
            ImageView foto = (ImageView) findViewById(R.id.exibeFoto);

            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduz = Bitmap.createScaledBitmap(bitmap,150,150, true);
            foto.setImageBitmap(bitmapReduz);
            foto.setTag(caminhoFoto);
        }
        
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int[] categorias = {R.array.array_aparelhos,R.array.array_pecas,R.array.array_outros};


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,categorias[i], android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSubCategoria.setAdapter(adapter);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
