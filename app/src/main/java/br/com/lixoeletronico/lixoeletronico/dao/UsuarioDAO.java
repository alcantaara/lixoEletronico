package br.com.lixoeletronico.lixoeletronico.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

/**
 * Created by Marina on 03/05/2018.
 */

public class UsuarioDAO extends SQLiteOpenHelper {


    public UsuarioDAO(Context context) {
        super(context, "Dados", null, 4);
    }

    //Com a criação do banco de dados será criada uma tabela com os dados do usuário
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuarios (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, cpf INTEGER NOT NULL, email TEXT NOT NULL, senha TEXT NOT NULL, confSenha TEXT NOT NULL);";


       String sqlb = "CREATE TABLE Agendar ("+
                " id_usuario INTEGER NOT NULL,"+
                " endereco TEXT NOT NULL,"+
                " numero INTEGER NOT NULL,"+
                " complemento TEXT,"+
                " bairro TEXT NOT NULL,"+
                " data TEXT NOT NULL,"+
                " horario TEXT NOT NULL,"+
                " subcategoria TEXT NOT NULL,"+
                " foto TEXT,"+
                " quantidade TEXT NOT NULL);";

        db.execSQL(sql);
        db.execSQL(sqlb);
    }
    //como ainda não teremos atualizações, caso alguma alteração seja feita no banco de dados
    // e exclui a tabela antiga e criada uma com as novas alterações
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Usuarios";
        db.execSQL(sql);
        String sqlb = "DROP TABLE IF EXISTS Agendar";
        db.execSQL(sqlb);
        onCreate(db);
    }

    public void insere(Usuario usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("nome",usuario.getNome());
        dados.put("cpf",usuario.getCpf());
        dados.put("email",usuario.getEmail());
        dados.put("senha",usuario.getSenha());
        dados.put("confSenha",usuario.getConfSenha());

       db.insert("Usuarios",null,dados); // insere na tabela Usuarios os dados passados a cima
    }

    public void insereAgendar(Agenda agenda) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();

        dados.put("id_usuario",agenda.getId_usuario());
        dados.put("endereco",agenda.getEndereco());
        dados.put("numero",agenda.getNumero());
        dados.put("complemento",agenda.getComplemento());
        dados.put("bairro",agenda.getBairro());
        dados.put("data",agenda.getData());
        dados.put("horario",agenda.getHorario());
        dados.put("subcategoria",agenda.getSubCategoria());
        dados.put("foto",agenda.getFoto());
        dados.put("quantidade",agenda.getQuantidade());


        db.insert("Agendar",null,dados); // insere na tabela Agenda os dados passados a cima
    }

    public int isBuscaUsuario(String email, String senha){
        String sql = "SELECT email,senha, id FROM Usuarios WHERE email = ?;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{email});
        String emaildb = null;
        String senhadb = null;
        Integer cont = 0;
        if(cursor.moveToFirst()){
            cont++;
            emaildb = cursor.getString(cursor.getColumnIndex("email"));
            senhadb = cursor.getString(cursor.getColumnIndex("senha"));
            Log.d("testeemail", emaildb);
            Log.d("testeemail", senhadb);
        }
        if(cont > 0){
                if(senha.equals(senhadb)){
                cursor.close();
                return 1;
                }
            return 2;
        }
        cursor.close();
        return 3;
    }

    public String buscaID(String email){
        String sql = "SELECT id FROM Usuarios WHERE email = ?;";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,new String[]{email});
        String id = null;
        if(cursor.moveToFirst())
        {
            id = cursor.getString(cursor.getColumnIndex("id"));

        }
        return id;
    }

    public List<Agenda> buscaAgendamentos(String id) {
        String sql = "SELECT * FROM Agendar WHERE id_usuario = ?;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,new String[]{id});
        String a,b;

        List<Agenda> agendas = new ArrayList<Agenda>();
        while (c.moveToNext()){
            Agenda agenda = new Agenda();
            agenda.setId_usuario(c.getInt(c.getColumnIndex("id_usuario")));
            agenda.setEndereco(c.getString(c.getColumnIndex("endereco")));
            agenda.setNumero(c.getInt(c.getColumnIndex("numero")));
            agenda.setComplemento(c.getString(c.getColumnIndex("complemento")));
            agenda.setBairro(c.getString(c.getColumnIndex("bairro")));
            agenda.setData(c.getString(c.getColumnIndex("data")));
            agenda.setHorario(c.getString(c.getColumnIndex("horario")));
            agenda.setSubCategoria(c.getString(c.getColumnIndex("subcategoria")));
            agenda.setFoto(c.getString(c.getColumnIndex("foto")));
            agenda.setQuantidade(c.getString(c.getColumnIndex("quantidade")));

            a = c.getString(c.getColumnIndex("endereco"));
            b = c.getString(c.getColumnIndex("complemento"));
            Log.d("testebusca", a);
            Log.d("testebusca", b);
            agendas.add(agenda);
        }

        c.close();

        return agendas;
    }
}