package systemplus.com.br.aluraandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import systemplus.com.br.aluraandroid.adapter.AlunosAdapter;
import systemplus.com.br.aluraandroid.dao.AlunoDAO;
import systemplus.com.br.aluraandroid.model.Aluno;
import systemplus.com.br.aluraandroid.model.EnviaAlunosTask;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    public AlunoDAO alunoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liasta_alunos);

        carregaListaDeAlunos();

        eventoBotaoNovoAluno();

        itemDaListaAlterar();

        registerForContextMenu(listaAlunos);
    }

    private void itemDaListaAlterar() {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent formularioIntent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                formularioIntent.putExtra("aluno", aluno);

                startActivity(formularioIntent);
            }
        });
    }

    private void eventoBotaoNovoAluno() {
        Button botaoNovoAluno = (Button) findViewById(R.id.adiciona_novo);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(vaiProFormulario);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alunos, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
                new EnviaAlunosTask(this).execute();
                break;
            case R.id.menu_baixar_provas:
                Intent vaiPraListaDeProvas = new Intent(this, ProvasActivity.class);
                startActivity(vaiPraListaDeProvas);
                break;
            case R.id.menu_baixar_mapa:
                Intent vaiParaOMapa = new Intent(this, MapaActivity.class);
                startActivity(vaiParaOMapa);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregaListaDeAlunos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        intenteLigar(menu, aluno);

        intenteSMS(menu, aluno);

        intentSite(menu, aluno);

        intenteEndereco(menu, aluno);

        intentDeletar(menu, aluno);
    }

    private void intentDeletar(ContextMenu menu, final Aluno aluno) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                alunoDao = new AlunoDAO(ListaAlunosActivity.this);
                alunoDao.deletar(aluno);
                alunoDao.close();

                carregaListaDeAlunos();

                return false;
            }
        });
    }

    private void intentSite(ContextMenu menu, Aluno aluno) {
        MenuItem itemSite = menu.add("Visitar Site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);

        String site = aluno.getSite();
        if (!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        itemSite.setIntent(intentSite);
    }

    private void intenteSMS(ContextMenu menu, Aluno aluno) {
        MenuItem itemSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + aluno.getTelefone()));
        itemSMS.setIntent(intentSMS);
    }

    private void intenteEndereco(ContextMenu menu, Aluno aluno) {
        MenuItem itemMapa = menu.add("Visualizar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=+" + aluno.getEndereco()));
        itemMapa.setIntent(intentMapa);
    }

    private void intenteLigar(ContextMenu menu, final Aluno aluno) {
        MenuItem itemTel = menu.add("Ligar");
        itemTel.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                } else {
                    Intent intentTell = new Intent(Intent.ACTION_CALL);
                    intentTell.setData(Uri.parse("tel:" + aluno.getTelefone()));
                    startActivity(intentTell);
                }
                return false;
            }
        });
    }

    private void carregaListaDeAlunos() {
        listaAlunos = (ListView) findViewById(R.id.listAlunos);
        AlunosAdapter adapter = new AlunosAdapter(ListaAlunosActivity.this, listaAlunos());

        listaAlunos.setAdapter(adapter);
    }

    public List<Aluno> listaAlunos() {
        alunoDao = new AlunoDAO(this);
        List<Aluno> alunos = alunoDao.listaDeAlunos();
        alunoDao.close();
        return alunos;
    }
}
