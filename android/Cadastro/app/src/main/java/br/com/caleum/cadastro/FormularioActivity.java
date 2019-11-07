package br.com.caleum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.caleum.cadastro.constantes.Constantes;
import br.com.caleum.cadastro.dominio.Aluno;
import br.com.caleum.cadastro.helper.FormularioHelper;
import br.com.caleum.cadastro.persistencia.AlunoDAO;

public class FormularioActivity extends AppCompatActivity {


    private FormularioHelper helper;
    private String localArquivoFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_formulario);

        //Toast.makeText(this, "Item selecionado: " + getIntent().getIntExtra("posicao", -1), Toast.LENGTH_LONG).show();
        Aluno aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        helper = new FormularioHelper(this);

        // Altera nome da activity
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.incluirAluno);
//        ab.setSubtitle("teste");
        if(aluno != null){
            helper.preencherFormulario(aluno);
            ab.setTitle(R.string.alterarAluno);

        }
//        Button botaoSalvar = (Button) findViewById(R.id.botaoSalvar);
//
//        botaoSalvar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i("FormularioActivity", "onClick()");
//                Toast.makeText(FormularioActivity.this, "Pegadinha do malandro!", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
        Button botaoFoto = helper.getBotaoFoto();
        botaoFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                localArquivoFoto = getExternalFilesDir(null) + File.separator + System.currentTimeMillis() + Constantes.EXTENSAO_FOTO;
                Log.i("FormularioActivity", "onClick() - " + localArquivoFoto);
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localArquivoFoto)));
                startActivityForResult(irParaCamera, Constantes.REQUEST_CAMERA);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constantes.REQUEST_CAMERA){
            if(resultCode == Activity.RESULT_OK){
                helper.carregarImagem(this.localArquivoFoto);
            }else{
                this.localArquivoFoto = null;
//                Toast.makeText(this, R.string.mensagemErroAcessoFoto, Toast.LENGTH_SHORT);
                Log.i("FormularioActivity", "retorno do tirar foto: "  + resultCode);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_menu_salvar:
//                Toast.makeText(FormularioActivity.this, "Rá!", Toast.LENGTH_SHORT).show();
                Aluno aluno = helper.pegaAlunoDoFormulario();
                Log.d("FormularioActivity", "aluno: " + aluno);
                if(helper.temNome()){
                    AlunoDAO dao = new AlunoDAO(this);
                    if(aluno.getId() != null){
                        dao.alterar(aluno);
                    }else{
                        dao.inserir(aluno);
                    }
                    dao.close();
                    finish();
                    Toast.makeText(FormularioActivity.this, aluno.getId() != null ? R.string.alteradoComSucesso : R.string.incluidoComSucesso, Toast.LENGTH_SHORT).show();
                }else{
                    helper.mostrarErro();
                }

                return true;
            default:
                // o padrão é true, ou seja, não propaga outros eventos, caso existam.
                return super.onOptionsItemSelected(item);

        }


    }
}
