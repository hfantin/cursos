package br.com.caleum.cadastro.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caleum.cadastro.constantes.TabelaAlunos;
import br.com.caleum.cadastro.dominio.Aluno;

/**
 * Created by android6345 on 08/09/16.
 *
 *  * abrir sqlite: adb -s DEVICE_NAME shell
 *        no shell, digite sqlite3 /data/data/br.com.caleum.cadastro/databases/CadastroAluno.db
 *
 * greendown - orm
 *
 * android arsenal - bibliotecas
 */
public class AlunoDAO extends SQLiteOpenHelper {


    private static final int VERSAO_DB = 2;
    private static final String DB_CADASTRO_ALUNO = "CadastroAluno.db";
    public AlunoDAO(Context context) {
        super(context, DB_CADASTRO_ALUNO, null, VERSAO_DB);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("AlunoDAO", "onCreate");
        String sql = "CREATE TABLE " + TabelaAlunos.NOME_TABELA.getDescricao()
                + "("
                + TabelaAlunos.COLUNA_ID.getDescricao()+" INTEGER PRIMARY KEY, "
                + TabelaAlunos.COLUNA_NOME.getDescricao() + " TEXT NOT NULL, "
                + TabelaAlunos.COLUNA_TELEFONE.getDescricao() + " TEXT, "
                + TabelaAlunos.COLUNA_ENDERECO.getDescricao() + " TEXT, "
                + TabelaAlunos.COLUNA_SITE.getDescricao() + " SITE TEXT, "
                + TabelaAlunos.COLUNA_NOTA.getDescricao() + " NOTA REAL, "
                + TabelaAlunos.COLUNA_CAMINHO_FOTO.getDescricao() +" TEXT "
                + ");";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {
        Log.i("AlunoDAO", "onCreate - versaoAntiga=" + versaoAntiga + ", versaoNova="+versaoNova);
        // Apaga a tabela inteira: cuidado!
//        String sqlApagarTabela ="DROP TABLE IF EXISTS " + TabelaAlunos.NOME_TABELA.getDescricao() + ";";
        String sqlAlterarTabela = "ALTER TABLE " + TabelaAlunos.NOME_TABELA.getDescricao() + " ADD COLUMN " + TabelaAlunos.COLUNA_CAMINHO_FOTO.getDescricao() + " TEXT;";
        db.execSQL(sqlAlterarTabela);
//        onCreate(db);
    }

    public void inserir(final Aluno aluno){
        getWritableDatabase().insert(TabelaAlunos.NOME_TABELA.getDescricao(), null,obterValores(aluno));
    }

    public void excluir(final Aluno aluno){
        getWritableDatabase().delete(TabelaAlunos.NOME_TABELA.getDescricao(), TabelaAlunos.COLUNA_ID.getDescricao() + "=?", new String [] {aluno.getId().toString()});
    }
    public void alterar(final Aluno aluno){
        getWritableDatabase().update(TabelaAlunos.NOME_TABELA.getDescricao(), obterValores(aluno), TabelaAlunos.COLUNA_ID.getDescricao() + "=?", new String [] {aluno.getId().toString()});
    }

    public List<Aluno> consultar(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TabelaAlunos.NOME_TABELA.getDescricao() + ";", null);
        while(cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getLong(cursor.getColumnIndex(TabelaAlunos.COLUNA_ID.getDescricao())));
            aluno.setNome(cursor.getString(cursor.getColumnIndex(TabelaAlunos.COLUNA_NOME.getDescricao())));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex(TabelaAlunos.COLUNA_TELEFONE.getDescricao())));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex(TabelaAlunos.COLUNA_ENDERECO.getDescricao())));
            aluno.setSite(cursor.getString(cursor.getColumnIndex(TabelaAlunos.COLUNA_SITE.getDescricao())));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex(TabelaAlunos.COLUNA_NOTA.getDescricao())));
            aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex(TabelaAlunos.COLUNA_CAMINHO_FOTO.getDescricao())));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;

    }

    private ContentValues obterValores(Aluno aluno){
        ContentValues valores = new ContentValues();
        valores.put(TabelaAlunos.COLUNA_NOME.getDescricao(), aluno.getNome());
        valores.put(TabelaAlunos.COLUNA_TELEFONE.getDescricao(), aluno.getTelefone());
        valores.put(TabelaAlunos.COLUNA_ENDERECO.getDescricao(), aluno.getEndereco());
        valores.put(TabelaAlunos.COLUNA_SITE.getDescricao(), aluno.getSite());
        valores.put(TabelaAlunos.COLUNA_NOTA.getDescricao(), aluno.getNota());
        valores.put(TabelaAlunos.COLUNA_CAMINHO_FOTO.getDescricao(), aluno.getCaminhoFoto());
        return valores;
    }

    public boolean isAluno(String telefone){
        String parametros [] =  {telefone};
        Cursor cursor = getReadableDatabase().rawQuery("SELECT 1 FROM " + TabelaAlunos.NOME_TABELA.getDescricao() + " WHERE telefone = ?;", parametros);
        int total = cursor.getCount();
        cursor.close();
        return total > 0;
    }

}
