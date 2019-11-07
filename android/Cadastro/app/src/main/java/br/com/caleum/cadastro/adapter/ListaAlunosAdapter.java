package br.com.caleum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import br.com.caleum.cadastro.R;
import br.com.caleum.cadastro.dominio.Aluno;

/**
 * Created by Hamilton on 13/09/16.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;


    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
        this.alunos = alunos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int posicao) {
        return alunos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return ((Aluno) getItem(posicao)).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.lista, parent, false);
        Aluno aluno = alunos.get(posicao);
        TextView nome = (TextView) v.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());
        ImageView foto = (ImageView) v.findViewById(R.id.item_foto);
        // campos do layout landscape
        TextView telefone = (TextView) v.findViewById(R.id.item_telefone);
        TextView site = (TextView) v.findViewById(R.id.item_site);
        if(telefone != null){
            telefone.setText(aluno.getTelefone());
        }
        if(site != null){
            site.setText(aluno.getSite());
        }



        Bitmap bmFoto = null;
        if(aluno.getCaminhoFoto() != null && new File(aluno.getCaminhoFoto()).exists()){
            bmFoto = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
//            foto.setBackgroundColor(activity.getResources().getColor(R.color.linhaPar));
        }else{
            bmFoto = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_sem_foto);
//            foto.setBackgroundColor(activity.getResources().getColor(R.color.linhaImpar));
        }
        bmFoto = Bitmap.createScaledBitmap(bmFoto, 200 , 200, true);
        foto.setImageBitmap(bmFoto);

        // zebrado
//        v.setBackgroundColor(activity.getResources().getColor(posicao % 2 == 0 ? R.color.linhaPar : R.color.linhaImpar));

        return v;
    }
}

