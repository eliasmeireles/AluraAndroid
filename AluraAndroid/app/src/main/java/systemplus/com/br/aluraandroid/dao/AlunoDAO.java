package systemplus.com.br.aluraandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import systemplus.com.br.aluraandroid.generator.GeradorDeId;
import systemplus.com.br.aluraandroid.model.Aluno;

/**
 * Created by elias on 25/08/2016.
 */
public class AlunoDAO extends SQLiteOpenHelper {


    private SQLiteDatabase database;

    public AlunoDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Alunos(id INTENGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT, " +
                "telefone TEXT, " +
                "site TEXT, " +
                "nota REAL, " +
                "fotoPath TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "ALTER TABLE Alunos ADD COLUMN fotoPath TEXT";
        db.execSQL(sql);
    }

    public boolean inserir(Aluno aluno) {
        try {
            database = getWritableDatabase();

            ContentValues values = getContentValuesAluno(aluno);

            database.insert("Alunos", null, values);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    @NonNull
    private ContentValues getContentValuesAluno(Aluno aluno) {
        ContentValues values = new ContentValues();
        if (aluno.getId() == null) {
            aluno.setId(new GeradorDeId().geraId());
        }

        values.put("id", aluno.getId());
        values.put("nome", aluno.getNome());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("fotoPath", aluno.getFotoPath());
        return values;
    }

    public List<Aluno> listaDeAlunos() {

        String sql = "SELECT * FROM Alunos";
        database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);
        List<Aluno> alunos = new ArrayList<Aluno>();

        preencheListaDeAlunos(cursor, alunos);

        cursor.close();

        return alunos;
    }

    private void preencheListaDeAlunos(Cursor cursor, List<Aluno> alunos) {
        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getLong(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            aluno.setFotoPath(cursor.getString(cursor.getColumnIndex("fotoPath")));

            alunos.add(aluno);
        }
    }

    public void deletar(Aluno aluno) {
        database = getWritableDatabase();
        String[] params = {aluno.getId().toString()};
        database.delete("Alunos", "id = ?", params);
    }

    public boolean alterar(Aluno aluno) {
        try {
            database  = getWritableDatabase();

            ContentValues values = getContentValuesAluno(aluno);

            String[] params = {aluno.getId().toString()};
            database.update("Alunos", values, "id = ?", params);
            return true;

        } catch (Exception e) {
        }
        return false;
    }
}
