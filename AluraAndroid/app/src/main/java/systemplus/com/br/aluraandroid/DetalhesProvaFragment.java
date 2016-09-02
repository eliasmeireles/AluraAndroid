package systemplus.com.br.aluraandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import systemplus.com.br.aluraandroid.model.Prova;


public class DetalhesProvaFragment extends Fragment {
    private ListView listaTopicos;
    private TextView provaMateria;
    private TextView provaData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);

        listaTopicos = (ListView) view.findViewById(R.id.detalhes_prova_topicos);
        provaMateria = (TextView) view.findViewById(R.id.detalhes_prova_materia);
        provaData = (TextView) view.findViewById(R.id.detalhes_prova_data);

        Bundle paramentros = getArguments();

        if (paramentros != null) {
            Prova prova = (Prova) paramentros.getSerializable("prova");
            populaCampos(prova);
        }
        return view;
    }

    public void populaCampos(Prova prova) {
        provaMateria.setText(prova.getMateria());
        provaData.setText(prova.getData());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1,
                        prova.getTopicos());

        listaTopicos.setAdapter(adapter);
    }
}
