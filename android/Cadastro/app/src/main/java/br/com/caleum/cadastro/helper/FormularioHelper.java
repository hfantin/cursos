package br.com.caleum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.File;

import br.com.caleum.cadastro.FormularioActivity;
import br.com.caleum.cadastro.R;
import br.com.caleum.cadastro.dominio.Aluno;

/**
 * Created by Hamilton on 08/09/16.
 */
public class FormularioHelper {


    private Aluno aluno;
    private ImageView foto;
    private Button botaoFoto;
    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    public FormularioHelper(FormularioActivity activity) {
        foto = (ImageView) activity.findViewById(R.id.foto);
        botaoFoto = (Button) activity.findViewById(R.id.botao_foto);
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        nota = (RatingBar) activity.findViewById(R.id.nota);
        aluno = new Aluno();

    }

    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String) foto.getTag());
        return aluno;
    }

    public void preencherFormulario(final Aluno novoAluno){
        aluno.setId(novoAluno.getId());
        nome.setText(novoAluno.getNome());
        telefone.setText(novoAluno.getTelefone());
        endereco.setText(novoAluno.getEndereco());
        site.setText(novoAluno.getSite());
        nota.setProgress(novoAluno.getNota().intValue());
        carregarImagem(novoAluno.getCaminhoFoto());
    }

    public boolean temNome(){
        return ! (nome.getText().toString().isEmpty() || nome.getText().toString().length() < 3);
    }

    public void mostrarErro(){
        nome.setError("Nome inválido!");
    }

    public Button getBotaoFoto(){
        return botaoFoto;
    }

    public void carregarImagem(String localArquivoFoto) {
        if(localArquivoFoto!=null && !localArquivoFoto.trim().isEmpty()){
            if(new File(localArquivoFoto).exists()){
                Bitmap bmFoto = BitmapFactory.decodeFile(localArquivoFoto);
                Bitmap bmReduzido = Bitmap.createScaledBitmap(bmFoto, bmFoto.getWidth(), 300, true);
                foto.setImageBitmap(bmReduzido);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);
                foto.setTag(localArquivoFoto);
            }

        }

    }
}
