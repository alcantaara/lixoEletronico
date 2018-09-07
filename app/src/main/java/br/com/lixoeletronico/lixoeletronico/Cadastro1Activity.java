package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import br.com.lixoeletronico.lixoeletronico.dao.UsuarioDAO;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

public class Cadastro1Activity extends AppCompatActivity {

    private CadastroHelper helper;
    private TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText eNome = (EditText) findViewById(R.id.cadastroNome);
        final EditText eCpf = (EditText) findViewById(R.id.cadastroCpf);
        final EditText eEmail = (EditText) findViewById(R.id.cadastroEmail);
        final EditText eSenha = (EditText) findViewById(R.id.cadastroSenha);

        final String nome = eNome.getText().toString();
        final String cpf = eCpf.getText().toString();
        final String email = eEmail.getText().toString();
        final String senha = eSenha.getText().toString();

        helper = new CadastroHelper(this);

        Button continuar = (Button) findViewById(R.id.cadastroContinuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Usuario usuario = helper.pegaUsuario();
                Log.d("teste", usuario.getNome());
                UsuarioDAO dao = new UsuarioDAO(Cadastro1Activity.this);
                dao.insere(usuario);
                dao.close();//fechar conexao com banco de dados

                Intent irContinuar = new Intent(Cadastro1Activity.this, LoginActivity.class);
                startActivity(irContinuar);

                Toast.makeText(Cadastro1Activity.this,"Usuário cadastrado!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
