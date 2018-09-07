package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide(); //para não exibir a barra de menu
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //criação da tela e referencia a tela de criaçao

        final EditText email = (EditText) findViewById(R.id.loginEmail);
        final EditText senha = (EditText) findViewById(R.id.loginSenha);
        final Button cadastro = (Button) findViewById(R.id.loginCadastrar);
        final Button login = (Button) findViewById(R.id.loginLogin);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            //acao que sera tomada após o clique no botao
            //ira da tela Login para Cadastro1
            public void onClick(View view) {

                Intent irCadastro = new Intent(LoginActivity.this, Cadastro1Activity.class);
                startActivity(irCadastro);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UsuarioDAO dao = new UsuarioDAO(LoginActivity.this);
                int result = dao.isBuscaUsuario(email.getText().toString(),senha.getText().toString());
                dao.close();//fechar conexao com banco de dados
                Log.v("idbanco", "teste " + result);


                Intent irMenu = new Intent(LoginActivity.this, MenuActivity.class);
                Usuario usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                //irMenu.putExtra("email", login.getEmail());
                startActivity(irMenu);
                Toast.makeText(LoginActivity.this,"Bem-vindo!", Toast.LENGTH_LONG).show();
                //finish()
/*
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
                }*/
            }
        });

    }
}
