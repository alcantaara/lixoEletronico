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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Field;

public class AparelhosActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener  {

    public static final int REQUEST_CODE = 1;
    private Spinner spCategoria;
    private Spinner spSubCategoria;
    public String caminhoFoto, subCategoria, qtd;
    private AgendarHelper helper;
    private Button agendar;
    private EditText quantidade;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparelhos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spSubCategoria = (Spinner) findViewById(R.id.spSubCategoria);
        quantidade = (EditText) findViewById(R.id.agenda_quantidade);


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
                qtd = quantidade.getText().toString();
                Intent i = getIntent();
                String endereco = i.getStringExtra("endereco");
                String numero = i.getStringExtra("numero");
                String bairro = i.getStringExtra("bairro");
                String complemento = i.getStringExtra("complemeto");
                String data = i.getStringExtra("data");
                String hora = i.getStringExtra("hora");
                String id = i.getStringExtra("id");
                String id_usuario = id;
                String numerocasa = numero;

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
                api.coleta(

                        //Passing the values by getting it from editTexts
                        id_usuario,
                        endereco,
                        numerocasa,
                        complemento,
                        bairro,
                        data,
                        hora,
                        subCategoria,
                        caminhoFoto,
                        qtd,


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

                                    //Displaying the output as a toast
                                    Toast.makeText(AparelhosActivity.this, "Agendamento salvo!", Toast.LENGTH_LONG).show();
                                Intent agendou = new Intent(AparelhosActivity.this, MenuActivity.class);
                                startActivity(agendou);


                            }



                            @Override
                            public void failure(RetrofitError error) {
                                //If any error occured displaying the error as toast
                                Toast.makeText(AparelhosActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                );


                /*final EditText quantidade = (EditText) findViewById(R.id.agenda_quantidade);
                qtd = quantidade.getText().toString();

                helper = new AgendarHelper(id_usuario,endereco,numerocasa,complemento,bairro,data,hora,subCategoria,caminhoFoto,qtd);
                Agenda agenda = helper.pegaAgenda();
                agenda.Imprime();
                UsuarioDAO usuarioDAO = new UsuarioDAO(AparelhosActivity.this);
                usuarioDAO.insereAgendar(agenda);
                usuarioDAO.close();

                Intent agendou = new Intent(AparelhosActivity.this, MenuActivity.class);
                startActivity(agendou);*/

                //Toast.makeText(AparelhosActivity.this,"Agedamento Salvo!", Toast.LENGTH_SHORT).show();

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
