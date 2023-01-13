package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contador = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contador);
        alunos.add(aluno);
        contador++;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void edit(Aluno aluno) {
        Aluno encontrado = buscaAluno(aluno);
        if (encontrado != null) {
            int posicao = alunos.indexOf(encontrado);
            alunos.set(posicao, aluno);
        }
    }

    public void remove(Aluno aluno) {
        Aluno encontrado = buscaAluno(aluno);
        if (encontrado != null) {
            alunos.remove(encontrado);
        }
    }

    private Aluno buscaAluno(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }
}
