package br.com.caleum.cadastro;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.converter.AlunoConverter;
import br.com.caelum.cadastro.web.EnviaAlunosTask;
import br.com.caelum.cadastro.web.WebClient;
import br.com.caleum.cadastro.adapter.ListaAlunosAdapter;
import br.com.caleum.cadastro.constantes.Constantes;
import br.com.caleum.cadastro.dominio.Aluno;
import br.com.caleum.cadastro.persistencia.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {


    private ListView lista;
    private Aluno alunoSelecionado;



    // atalhos: https://developer.android.com/studio/intro/index.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tenta exibir logo
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setLogo(R.mipmap.ic_launcher);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);

        setContentView(R.layout.activity_lista_alunos);
        lista = (ListView) findViewById(R.id.listaAlunos);
        Button botaoFlutuante = (Button) findViewById(R.id.botaoFlutuante);
        // listeners
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int posicao, long id) {
                Log.i("ItemClick", "posicao=" + posicao);
//                Toast.makeText(ListaAlunosActivity.this, "Item selecionado: " + posicao, Toast.LENGTH_LONG).show();
                Aluno aluno = (Aluno) lista.getItemAtPosition(posicao);
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

//        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View item, int posicao, long id) {
//                Log.i("ItemLongClick", "posicao=" + posicao);
//                String nome = lista.getItemAtPosition(posicao).toString(); // é igual lista.getAdapter().getItem(posicao).toString()
//                Toast.makeText(ListaAlunosActivity.this, "Item selecionado: " + nome, Toast.LENGTH_LONG).show();
//                return true; // processa somente esse evento
//            }
//        });

        botaoFlutuante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Click", "on click do floating button");
                //Toast.makeText(ListaAlunosActivity.this, "Em construção", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ListaAlunosActivity.this, FormularioActivity.class));

            }
        });
        // registra o Context menu
        registerForContextMenu(lista);

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.consultar();
        dao.close();
//        lista.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos));
        lista.setAdapter(new ListaAlunosAdapter(this,alunos));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Log.i("ListaAlunosActivity", "onCreateContextMenu");
//        final Aluno aluno =
        alunoSelecionado = (Aluno) lista.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
        Log.i("ListaAlunosActivity", "aluno=" + alunoSelecionado);
        // ligar
        MenuItem ligar = menu.add(R.string.ligar);
        //TODO verfificar permissão nos dispositivos android 6.0 +
        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem menuItem) {
                     String permissaoLigar = Manifest.permission.CALL_PHONE;
                     if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, permissaoLigar) == PackageManager.PERMISSION_GRANTED) {
                         fazerLigacao();
                     } else {
                         String[] permissoes = {permissaoLigar};
                         ActivityCompat.requestPermissions(ListaAlunosActivity.this, permissoes, Constantes.REQUEST_LIGAR);
                     }
                     return false;
                 }
             }
        );
//        Intent intentLigar = new Intent(Intent.ACTION_CALL);
//        intentLigar.setData(Uri.parse("tel:"+aluno.getTelefone()));
//        ligar.setIntent(intentLigar);
        // enviar sms
        MenuItem enviarSms = menu.add(R.string.enviarSms);
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
        intentSms.putExtra("sms_body", "e ae campeão");
        enviarSms.setIntent(intentSms);
        // achar no mapa
        MenuItem acharNoMapa = menu.add(R.string.acharNoMapa);
        Intent mapaIntent = new Intent(Intent.ACTION_VIEW);
        mapaIntent.setData(Uri.parse("geo:0,0?z=10&q=" + Uri.encode(alunoSelecionado.getEndereco())));
        acharNoMapa.setIntent(mapaIntent);
        // navegar no site
        MenuItem navegarNoSite = menu.add(R.string.navegarNoSite);
        Intent intentNavegar = new Intent(Intent.ACTION_VIEW);
        intentNavegar.setData(Uri.parse("http://" + alunoSelecionado.getSite()));
        navegarNoSite.setIntent(intentNavegar);
        // excluir
        MenuItem excluir = menu.add(R.string.excluir);
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // exibe alerta
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.excluir)
                        .setMessage(R.string.mensagemExcluir)
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                                        dao.excluir(alunoSelecionado);
                                        dao.close();
                                        carregarLista();

                                    }
                                }
                        )
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                return true; // permite ou não a propagação dos eventos, caso existam.
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissoes, @NonNull int[] resultados) {
        if (requestCode == Constantes.REQUEST_LIGAR) {
            if (resultados[0] == PackageManager.PERMISSION_GRANTED) {
                fazerLigacao();
            } else {
                Toast.makeText(this, R.string.mensagemErroAcessoLigacao, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("MissingPermission")
    private void fazerLigacao() {
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
        startActivity(intentLigar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_enviar_provas:
//                AlunoDAO dao = new AlunoDAO(this);
//                List<Aluno> alunos = dao.consultar();
//                dao.close();
//                String json = new AlunoConverter().toJSON(alunos);
//                WebClient wc = new WebClient();
//                String retorno = wc.doPost(json);
//                Toast.makeText(this, retorno, Toast.LENGTH_SHORT).show();
                new EnviaAlunosTask(this).execute();
                return true;
            case R.id.menu_baixar_provas:
                Intent provasIntent = new Intent(this, ProvasActivity.class);
                startActivity(provasIntent);
                return true;
            case R.id.menu_mapa:
                Intent mostrarMapa = new Intent(this, MostraAlunosActivity.class);
                startActivity(mostrarMapa);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
