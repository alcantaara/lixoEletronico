package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    private EditText email, senha;
    public Intent i;
    public Bundle bundle;
    private Button login;
    public String id;
    public static String acesso = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //para não exibir a barra de menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        acesso = "0";
        //criação da tela e referencia a tela de criaçao

        email = (EditText) findViewById(R.id.loginEmail);
        senha = (EditText) findViewById(R.id.loginSenha);
        final Button cadastro = (Button) findViewById(R.id.loginCadastrar);
        login = (Button) findViewById(R.id.loginLogin);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            //acao que sera tomada após o clique no botao
            //ira da tela Login para Cadastro1
            public void onClick(View view) {

                Intent irCadastro = new Intent(LoginActivity.this, Cadastro1Activity.class);
                startActivity(irCadastro);
            }
        });

        login.setOnClickListener(this);

        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);
                int result = dao.isBuscaUsuario(email.getText().toString(),senha.getText().toString());
                dao.close();//fechar conexao com banco de dados
                Log.v("idbanco", "teste " + result);

                if(result == 1){
                    Intent irMenu = new Intent(LoginActivity.this, MenuActivity.class);
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email.getText().toString());
                    //irMenu.putExtra("email", login.getEmail());
                    startActivity(irMenu);
                    Toast.makeText(LoginActivity.this,"Bem-vindo!", Toast.LENGTH_LONG).show();
                    //finish()
                }else if(result == 2) {
                    Toast.makeText(LoginActivity.this,"Senha Incorreta", Toast.LENGTH_LONG).show();
                    //finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Usuário Inválido", Toast.LENGTH_LONG).show();
                    //finish();
                }
            }
        });*/

    }

  /* private void buscaid(String emailb, String senhab){
       Toast.makeText(LoginActivity.this, emailb, Toast.LENGTH_LONG).show();
       Toast.makeText(LoginActivity.this, senhab, Toast.LENGTH_LONG).show();

        //Here we will handle the http request to insert user to mysql db
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegistroAPI api = adapter.create(RegistroAPI.class);

        //Defining the method insertuser of our interface
        api.buscaid(

                //Passing the values by getting it from editTexts
                emailb,
                senhab,

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
                        Toast.makeText(LoginActivity.this, output, Toast.LENGTH_LONG).show();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(output);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            id = obj.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        bundle.putString("id", id);

                    }



                    @Override
                    public void failure(RetrofitError error) {

                    }
                }
        );

    }*/

    private void login(){
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
        api.login(

                //Passing the values by getting it from editTexts
                email.getText().toString(),
                senha.getText().toString(),



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
                        if(output.equals("1")) {
                            //buscaid(email.getText().toString(),senha.getText().toString());
                            //Displaying the output as a toast
                            Toast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                            acesso = "1";
                            onClick(login);
                        }else{
                            Toast.makeText(LoginActivity.this, "Email ou Senha incorretos!", Toast.LENGTH_LONG).show();
                        }
                    }



                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClick(View v) {
        if(acesso.equals("1")){
            i = new Intent(v.getContext(), MenuActivity.class);
            Usuario usuario = new Usuario();
            usuario.setEmail(email.getText().toString());
            usuario.setSenha(senha.getText().toString());
            startActivity(i);
        }else{
            login();
        }
    }

}
