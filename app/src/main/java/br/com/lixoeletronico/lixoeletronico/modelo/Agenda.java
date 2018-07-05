package br.com.lixoeletronico.lixoeletronico.modelo;

import android.content.Intent;
import android.util.Log;

import java.util.Date;

/**
 * Created by Marina on 27/05/2018.
 */

public class Agenda {

    private Integer id_usuario;
    private String endereco;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String data;
    private String horario;
    private String subCategoria;
    private String foto;
    private String quantidade;

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Endereco: " + endereco + '\n' +
                "Numero: " + numero +'\n' +
                "Complemento: " + complemento + '\n' +
                "Bairro: " + bairro + '\n' +
                "Data: " + data + '\n' +
                "Horario: " + horario + '\n' +
                "Subcategoria: " + subCategoria + '\n' +
                "Foto: " + foto + '\n' +
                "Quantidade: " + quantidade + '\n';
    }

    public void Imprime(){
        Log.d("gty",getEndereco());
        Log.d("gty", String.valueOf(getNumero()).toString());
        Log.d("gty",getComplemento());
        Log.d("gty", getBairro());
        Log.d("gty",getData());
        Log.d("gty",getHorario());
        Log.d("gty",getSubCategoria());
//        Log.d("gty",getFoto());
        Log.d("gty",getQuantidade());
        Log.d("gty", String.valueOf(getId_usuario()).toString());
    }
}
