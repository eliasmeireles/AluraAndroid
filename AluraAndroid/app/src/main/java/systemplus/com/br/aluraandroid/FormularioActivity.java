package systemplus.com.br.aluraandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import systemplus.com.br.aluraandroid.dao.AlunoDAO;
import systemplus.com.br.aluraandroid.helper.FormularioHelper;
import systemplus.com.br.aluraandroid.model.Aluno;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private FormularioHelper helper;
    private String fotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");

        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }

        alunoBotaoFoto();
    }

    private void alunoBotaoFoto() {
        Button botaoFoto = (Button) findViewById(R.id.formulario_botao_foto);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                fotoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(fotoPath);

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));
                startActivityForResult(intentCamera, CODIGO_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
               helper.carregaImagem(fotoPath);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_salvar:
                Aluno aluno = helper.getAluno();
                AlunoDAO alunoDAO = new AlunoDAO(this);

                analiticDaoMethod(aluno, alunoDAO);

                alunoDAO.close();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void analiticDaoMethod(Aluno aluno, AlunoDAO alunoDAO) {
        boolean resultado;
        System.out.println(aluno.getId());

        if (aluno.getId() != null) {
            resultado = alunoDAO.alterar(aluno);

            if (resultado) {
                Toast.makeText(FormularioActivity.this, "Dados do aluno " + aluno.getNome() + " alterados com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FormularioActivity.this, "Os dados do aluno " + aluno.getNome() + " não foram alterados!", Toast.LENGTH_SHORT).show();
            }
        } else {
            resultado = alunoDAO.inserir(aluno);

            if (resultado) {
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FormularioActivity.this, "Aluno " + aluno.getNome() + " não foi salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
