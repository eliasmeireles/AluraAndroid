package systemplus.com.br.aluraandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;

import systemplus.com.br.aluraandroid.model.Prova;

public class ProvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_principal, new ListaProvasFragment());

        if (estaNoModoPaisagem()) {
            transaction.replace(R.id.frame_secundario, new DetalhesProvaFragment());
        }

        transaction.commit();
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    public void selecionaProva(Prova prova) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(!estaNoModoPaisagem()) {
            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();

            Bundle paramentros = new Bundle();
            paramentros.putSerializable("prova", prova);

            detalhesProva.setArguments(paramentros);

            transaction.replace(R.id.frame_principal, detalhesProva);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            DetalhesProvaFragment setalhesFragment =
                    (DetalhesProvaFragment) fragmentManager.findFragmentById(R.id.frame_secundario);
            setalhesFragment.populaCampos(prova);
        }
    }
}
