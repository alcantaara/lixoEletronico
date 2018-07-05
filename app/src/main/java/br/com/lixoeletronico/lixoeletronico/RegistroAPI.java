package br.com.lixoeletronico.lixoeletronico;

/**
 * Created by Douglas on 29/06/2018.
 */
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

public interface RegistroAPI {

    @FormUrlEncoded
    @POST("/cadastro2.php")
    public void insertUser(
            @Field("nome") String nome,
            @Field("cpf") String cpf,
            @Field("email") String email,
            @Field("senha") String senha,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/login.php")
    public void login(
            @Field("email") String email,
            @Field("senha") String senha,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/buscaid.php")
    public void buscaid(
            @Field("email") String email,
            @Field("senha") String senha,
            Callback<Response> callback);

    @FormUrlEncoded
    @POST("/dadoscoleta.php")
    public void dadoscoleta(
            @Field("id") String id,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/coleta.php")
    public void coleta(
            @Field("id_usuario") String idusuario,
            @Field("endereco") String endereco,
            @Field("numero") String numero,
            @Field("complemento") String complemento,
            @Field("bairro") String bairro,
            @Field("data") String data,
            @Field("horario") String horario,
            @Field("subcategoria") String subcategoria,
            @Field("foto") String foto,
            @Field("quantidade") String quantidade,
            Callback<Response> callback);


}



