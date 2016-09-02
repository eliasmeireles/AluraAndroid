package systemplus.com.br.meuempregocom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import systemplus.com.br.meuempregocom.R;
import systemplus.com.br.meuempregocom.converter.DateConverter;
import systemplus.com.br.meuempregocom.model.Usuario;

/**
 * Created by elias on 30/08/16.
 */
public class UsuarioAdapter extends BaseAdapter {

    private List<Usuario> listaDeUsuarios;
    private Context context;

    public UsuarioAdapter(List<Usuario> listaDeUsuarios, Context context) {
        this.listaDeUsuarios = listaDeUsuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaDeUsuarios.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDeUsuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = listaDeUsuarios.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.lista_usuarios, parent, false);
        }

        TextView campoNome = (TextView) view.findViewById(R.id.lista_usuario_nome);
        campoNome.setText(usuario.getNome());

        TextView campoEmail = (TextView) view.findViewById(R.id.lista_usuario_email);
        campoEmail.setText(usuario.getEmail());

        TextView campoDataCadastro = (TextView) view.findViewById(R.id.lista_usuario_data_cadastro);
        campoDataCadastro.setText(DateConverter.brasilianFormat(usuario.getDataCadastro().toString()));

        TextView campoCep = (TextView) view.findViewById(R.id.lista_usuario_endereco_cep);
        campoCep.setText(usuario.getEndereco().getCep());

        TextView campoLogradoudo = (TextView) view.findViewById(R.id.lista_usuario_endereco_logradouro);
        campoLogradoudo.setText(usuario.getEndereco().getLogradouro());

        TextView campoNumero = (TextView) view.findViewById(R.id.lista_usuario_endereco_numero);
        campoNumero.setText(usuario.getEndereco().getNumero());

        TextView campoComplemento = (TextView) view.findViewById(R.id.lista_usuario_endereco_complemento);
        campoComplemento.setText(usuario.getEndereco().getComplemento());

        TextView campoCidade = (TextView) view.findViewById(R.id.lista_usuario_endereco_cidade);
        campoCidade.setText(usuario.getEndereco().getCidade());

        TextView campoEstado = (TextView) view.findViewById(R.id.lista_usuario_endereco_estado);
        campoEstado.setText(usuario.getEndereco().getEstado().getNome());

        return view;
    }
}
