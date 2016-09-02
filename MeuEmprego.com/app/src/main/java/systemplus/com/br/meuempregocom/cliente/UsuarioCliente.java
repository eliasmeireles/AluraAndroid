package systemplus.com.br.meuempregocom.cliente;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import systemplus.com.br.meuempregocom.Service.UsuarioService;
import systemplus.com.br.meuempregocom.adapter.UsuarioAdapter;
import systemplus.com.br.meuempregocom.baseurl.BaseUrl;
import systemplus.com.br.meuempregocom.converter.DateConverter;
import systemplus.com.br.meuempregocom.converter.JacksonConverter;
import systemplus.com.br.meuempregocom.model.Usuario;
import systemplus.com.br.meuempregocom.model.UsuarioRequestResult;

/**
 * Created by elias on 30/08/16.
 */
public class UsuarioCliente extends AsyncTask<Void, Void, List<Usuario>> {

    private Context context;
    private UsuarioService service;
    private ProgressDialog dialog;
    private boolean requestCheck = true;
    private UsuarioAdapter adapter;
    private ListView listViewUsuarios;
    private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();


    public UsuarioCliente(Context context, ListView listViewUsuarios) {
        this.context = context;
        this.listViewUsuarios = listViewUsuarios;
    }

    public void pegaUsuarios() {
        service.getUsuarios(new Callback<UsuarioRequestResult>() {

            @Override
            public void success(UsuarioRequestResult usuarioRequestResult, Response response) {
                for (int i = 0; i < usuarioRequestResult.getContent().size(); i++) {
                    listaDeUsuarios.add(usuarioRequestResult.getContent().get(i));
                    if (i + 1 == usuarioRequestResult.getContent().size()) {
                        requestCheck = false;
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                System.out.println("O erro: " + error);
                requestCheck = false;
            }
        });
    }

    @Override
    protected List<Usuario> doInBackground(Void... params) {
        geradorDeService();
        while (requestCheck) {
        }
        return listaDeUsuarios;
    }

    private void geradorDeService() {
        JacksonConverter converter = new JacksonConverter(DateConverter.getHwObjectMapper());
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setConverter(converter)
                .setEndpoint(BaseUrl.BASE_URL)
                .build();

        adapter = new UsuarioAdapter(listaDeUsuarios, context);

        service = restAdapter.create(UsuarioService.class);

        pegaUsuarios();
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Carregando os dados...", true, true);
    }

    @Override
    protected void onPostExecute(List<Usuario> usuarios) {
        dialog.dismiss();
        carregaListaDeUsuarios();
    }

    private void carregaListaDeUsuarios() {
        listViewUsuarios.setAdapter(adapter);
    }
}
