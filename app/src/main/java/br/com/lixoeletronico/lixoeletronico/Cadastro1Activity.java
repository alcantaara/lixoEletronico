package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Cadastro1Activity extends AppCompatActivity {

    private CadastroHelper helper;
    private TextView nome;
    public String controlador = "0";
    private Button cadastrar;
    private EditText eNome, eCpf, eEmail, eSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);


        eNome = (EditText) findViewById(R.id.cadastroNome);
        eCpf = (EditText) findViewById(R.id.cadastroCpf);
        eEmail = (EditText) findViewById(R.id.cadastroEmail);
        eSenha = (EditText) findViewById(R.id.cadastroSenha);

        cadastrar = (Button) findViewById(R.id.cadastroContinuar);

        //Adding listener to button
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                api.insertUser(

                        //Passing the values by getting it from editTexts
                        eNome.getText().toString(),
                        eCpf.getText().toString(),
                        eEmail.getText().toString(),
                        eSenha.getText().toString(),


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
                                if(output.equals("2")) {
                                    //Displaying the output as a toast
                                    Toast.makeText(Cadastro1Activity.this, "Email já utilizado!", Toast.LENGTH_LONG).show();

                                }else{
                                    Intent i = new Intent(Cadastro1Activity.this, LoginActivity.class);
                                    startActivity(i);
                                    Toast.makeText(Cadastro1Activity.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }



                            @Override
                            public void failure(RetrofitError error) {
                                //If any error occured displaying the error as toast
                                Toast.makeText(Cadastro1Activity.this, error.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                );

            }
        });






        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //helper = new CadastroHelper(this);



/*
                Usuario usuario = helper.pegaUsuario();
                Log.d("teste", usuario.getNome());
                UsuarioDAO dao = new UsuarioDAO(Cadastro1Activity.this);
                dao.insere(usuario);
                dao.close();//fechar conexao com banco de dados

                Intent irContinuar = new Intent(Cadastro1Activity.this, LoginActivity.class);
                startActivity(irContinuar);

                Toast.makeText(Cadastro1Activity.this,"Usuário cadastrado!", Toast.LENGTH_LONG).show();
                finish();*/



    }

}
