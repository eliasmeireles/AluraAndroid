package systemplus.com.br.meuempregocom;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import systemplus.com.br.meuempregocom.cliente.UsuarioCliente;

public class ApplicationActivity extends AppCompatActivity {

    private ListView listViewUsuarios;
    private UsuarioCliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        listViewUsuarios = (ListView) findViewById(R.id.lista_de_usuarios);
        registerForContextMenu(listViewUsuarios);
        cliente = new UsuarioCliente(this, listViewUsuarios);
        cliente.execute();
    }
}
