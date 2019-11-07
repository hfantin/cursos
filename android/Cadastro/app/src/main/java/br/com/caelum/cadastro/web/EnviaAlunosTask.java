package br.com.caelum.cadastro.web;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caleum.cadastro.dominio.Aluno;
import br.com.caleum.cadastro.persistencia.AlunoDAO;

/**
 * Created by Hamilton on 14/09/16.
 */
public class EnviaAlunosTask extends AsyncTask<String, Void, String> {


    private Context context;
    private ProgressDialog pd;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = ProgressDialog.show(context, "Carregando...", "Enviando alunos", true, true);

    }

    @Override
    protected String doInBackground(String... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.consultar();
        dao.close();
        String retorno = new AlunoConverter().toJSON(alunos);
        return new WebClient().doPost(retorno);
    }

    @Override
    protected void onPostExecute(String resposta) {
        pd.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
    }
}
