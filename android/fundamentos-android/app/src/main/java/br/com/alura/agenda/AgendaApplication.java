package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Hamilton", "999998888", "hamilton@gmail.com"));
        dao.salva(new Aluno("Mari", "999997777", "mari@gmail.com"));
    }
}
