package systemplus.com.br.meuempregocom.Service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import systemplus.com.br.meuempregocom.model.Usuario;
import systemplus.com.br.meuempregocom.model.UsuarioRequestResult;

/**
 * Created by elias on 30/08/16.
 */
public interface UsuarioService {

    @GET("/usuarios")
    void getUsuarios(Callback<UsuarioRequestResult> callback);

    @GET("/usuarios/{id}")
    void getUsuarioById(@Path("id") String id, Callback<List<Usuario>> callback);


    @PUT("/usuarios/{id}")
    void atualizadoDados(@Path("id") String id, @Body Usuario usuario, Callback<List<Usuario>> callback);

    @POST("/usuarios")
    void creaNovoUsuario(@Body Usuario usuario, Callback<List<Usuario>> callback);


    @DELETE("/usuarios/{id}")
    void removerUsuario(@Path("id") String id, Callback<Usuario> callback);

}
