package br.com.alura.agenda.ui.activity;

import static br.com.alura.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.asynctask.BuscaTodosTelefonesDoAlunoTask;
import br.com.alura.agenda.asynctask.EditaAlunoTask;
import br.com.alura.agenda.asynctask.SalvaAlunoTask;
import br.com.alura.agenda.database.AgendaDatabase;
import br.com.alura.agenda.database.dao.AlunoDao;
import br.com.alura.agenda.database.dao.TelefoneDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText campoNome;

    private EditText campoTelefoneFixo;

    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private AlunoDao alunoDao;
    private TelefoneDao telefoneDao;
    private Aluno aluno;

    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        this.alunoDao = AgendaDatabase.getInstance(this).getAlunoDao();
        telefoneDao = AgendaDatabase.getInstance(this).getTelefoneDao();
        inicializacaoDosCampos();
        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {
        new BuscaTodosTelefonesDoAlunoTask(telefoneDao, aluno, telefones -> {
            this.telefonesDoAluno = telefones;
            for (Telefone telefone : telefonesDoAluno) {
                if (telefone.getTipo() == TipoTelefone.FIXO) {
                    campoTelefoneFixo.setText(telefone.getNumero());
                } else {
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();
    }

    private void finalizaFormulario() {
        preencheAluno();
        Telefone telefoneFixo = criarTelefone(campoTelefoneFixo, TipoTelefone.FIXO);
        Telefone telefoneCelular = criarTelefone(campoTelefoneCelular, TipoTelefone.CELULAR);
        if (aluno.temIdValido()) {
            editaAluno(telefoneFixo, telefoneCelular);
        } else {
           salvaAluno(telefoneFixo, telefoneCelular);
        }
//        finish();
    }

    private Telefone criarTelefone(EditText campoTelefoneFixo, TipoTelefone fixo) {
        String numero = campoTelefoneFixo.getText().toString();
        return new Telefone(numero, fixo);
    }

    private void salvaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
        new SalvaAlunoTask(alunoDao,
                aluno,
                telefoneFixo,
                telefoneCelular,
                telefoneDao,
                this::finish)
        .execute();
    }

    private void editaAluno(Telefone telefoneFixo, Telefone telefoneCelular) {
       new EditaAlunoTask(alunoDao, aluno, telefoneFixo,
               telefoneCelular, telefoneDao, telefonesDoAluno,
               this::finish).execute();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_telefone_fixo);
        campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_telefone_celular);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setEmail(email);
    }
}
