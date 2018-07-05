package br.com.lixoeletronico.lixoeletronico;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;
import br.com.lixoeletronico.lixoeletronico.modelo.Usuario;

/**
 * Created by Marina on 27/05/2018.
 */

public class AgendarHelper {

   /* private  EditText campoEndereco;
    private  EditText campoNumero;
    private  EditText campoComplemento;
    private  EditText campoBairro;
    private  String campoData;
    private  String campoHorario;
    private  String campoSubCategoria;
    private  String campoFoto;
    private  EditText campoQuantidade;*/

    private  Integer id_usuario;
    private  String campoEndereco;
    private  Integer campoNumero;
    private  String campoComplemento;
    private  String campoBairro;
    private  String campoData;
    private  String campoHorario;
    private  String campoSubCategoria;
    private  String campoFoto;
    private  String campoQuantidade;

    public AgendarHelper(int id, String campoEndereco, Integer campoNumero, String campoComplemento, String campoBairro, String campoData, String campoHorario, String campoSubCategoria, String campoFoto, String campoQuantidade) {
        this.id_usuario = id;
        this.campoEndereco = campoEndereco;
        this.campoNumero = campoNumero;
        this.campoComplemento = campoComplemento;
        this.campoBairro = campoBairro;
        this.campoData = campoData;
        this.campoHorario = campoHorario;
        this.campoSubCategoria = campoSubCategoria;
        this.campoFoto = campoFoto;
        this.campoQuantidade = campoQuantidade;
    }


   /* public AgendarHelper(EditText endereco, EditText numero, EditText complemento, EditText bairro){
        campoEndereco = endereco;
        campoNumero = numero;
        campoComplemento = complemento;
        campoBairro = bairro;
    }

    public AgendarHelper(String data, String horario){
        campoData = data;
        campoHorario = horario;
    }

    public AgendarHelper(String subcategoria, String foto, EditText qtd){
        campoSubCategoria = subcategoria;
        campoFoto = foto;
        campoQuantidade = qtd;
    }*/


    public Agenda pegaAgenda() {

       Agenda agenda = new Agenda();

        agenda.setId_usuario(this.id_usuario);
        agenda.setEndereco(this.campoEndereco);
        agenda.setNumero(this.campoNumero);
        agenda.setComplemento(this.campoComplemento);
        agenda.setBairro(this.campoBairro);
        agenda.setData(this.campoData);
        agenda.setHorario(this.campoHorario);
        agenda.setSubCategoria(this.campoSubCategoria);
        agenda.setFoto(this.campoFoto);
        agenda.setQuantidade(this.campoQuantidade);

        return agenda;
    }


}



