package br.com.lixoeletronico.lixoeletronico;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

/**
 * Created by Marina on 03/05/2018.
 */

public class CadastroHelper {

    private final EditText campoNome;
    private final EditText campoCpf;
    private final EditText campoEmail;
    private final EditText campoSenha;
    //private final EditText campoConfSenha;


    public CadastroHelper(Cadastro1Activity activity){
        campoNome = (EditText) activity.findViewById(R.id.cadastroNome);
        campoCpf = (EditText) activity.findViewById(R.id.cadastroCpf);
        campoEmail = (EditText) activity.findViewById(R.id.cadastroEmail);
        campoSenha = (EditText) activity.findViewById(R.id.cadastroSenha);
        //campoConfSenha = (EditText) activity.findViewById(R.id.cadastroConfSenha);

    }

    public Usuario pegaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome(campoNome.getText().toString());
        usuario.setCpf(campoCpf.getText().toString());
        usuario.setEmail(campoEmail.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        //usuario.setConfSenha(campoConfSenha.getText().toString());

        return usuario;
    }


}

