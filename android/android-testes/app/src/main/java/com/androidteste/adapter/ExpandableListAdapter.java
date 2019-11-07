package com.androidteste.adapter;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidteste.R;
import com.androidteste.model.Grupo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamilton on 09/12/16.
 */

public class ExpandableListAdapter  extends BaseExpandableListAdapter {

    private Activity context;
    private List<Grupo> grupos;
    private List<Grupo> gruposOriginal;

    public ExpandableListAdapter(Activity context, List<Grupo> grupos) {
        this.context = context;
        this.grupos = new ArrayList<>();
        this.gruposOriginal = new ArrayList<>();
        this.grupos.addAll(grupos);
        this.gruposOriginal.addAll(grupos);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return grupos.get(groupPosition).getCategorias().get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String TAG = this.getClass().getSimpleName();
        CategoryViewHolder categoryViewHolder;
        if (convertView == null) {
            Log.i(TAG, TAG + " getChildView - passou no if - infla o layout");
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
            categoryViewHolder = new CategoryViewHolder(convertView);
            convertView.setTag(categoryViewHolder);
        }else{
            Log.i(TAG, TAG + " getChildView - passou no else - reaproveita o layout");
            categoryViewHolder = (CategoryViewHolder) convertView.getTag();
        }


        final String category = (String) getChild(groupPosition, childPosition);
        categoryViewHolder.delete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                        grupos.get(groupPosition).getCategorias();
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        categoryViewHolder.item.setText(category);
        categoryViewHolder.botaoAdicionar.setVisibility(isLastChild ? View.VISIBLE : View.GONE);
        return convertView;

    }

    /**
     * Adiciona view na quantidade de elementos filhos.
     * @param groupPosition
     * @return
     */
    public int getChildrenCount(int groupPosition) {
        return grupos.get(groupPosition).getCategorias().size();
    }

    public Object getGroup(int groupPosition) {
        return grupos.get(groupPosition);
    }

    public int getGroupCount() {
        return grupos.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        String TAG = this.getClass().getSimpleName();
        if (convertView == null) {
            Log.i(TAG, TAG + " getGroupView - passou no if - infla o layout");
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        }else{
            Log.i(TAG, TAG + " getGroupView - passou no else - reaproveita layout");
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        Grupo grupo =  (Grupo) getGroup(groupPosition);
        String nomeGrupo = grupo.getNome();
        switch (grupo.getCodigo()){
            case 1:
                groupViewHolder.imagem.setImageResource(R.drawable.ic_casa);
                groupViewHolder.imagem.setColorFilter(Color.argb(255, 0, 255, 0));
                break;
            case 2:
                groupViewHolder.imagem.setImageResource(R.drawable.ic_educao);
                groupViewHolder.imagem.setColorFilter(Color.argb(255, 0, 0, 255));
                break;
            case 3:
                groupViewHolder.imagem.setImageResource(R.drawable.ic_auto);
                groupViewHolder.imagem.setColorFilter(Color.argb(255, 255, 0, 0));
                break;
            default:
                groupViewHolder.imagem.setImageResource(R.drawable.ic_interrogacao);
                groupViewHolder.imagem.setColorFilter(Color.argb(255, 0, 0, 0));

        }
        groupViewHolder.item.setTypeface(null, Typeface.BOLD);
        groupViewHolder.item.setText(nomeGrupo);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){
        query = query.toLowerCase();
        Log.v("filtro", String.valueOf(grupos.size()));
        grupos.clear();

        if(query.isEmpty()){
            grupos.addAll(gruposOriginal);
        }
        else {

            for(Grupo grupo: gruposOriginal){

                List<String> categorias = grupo.getCategorias();
                List<String> lista = new ArrayList<String>();
                for(String categoria: categorias){
                    if(categoria.toLowerCase().contains(query)){
                        lista.add(categoria);
                    }
                }
                grupos.add(new Grupo(grupo.getCodigo(), grupo.getNome(), lista));
            }
        }

        Log.v("filtro", String.valueOf(grupos.size()));
        notifyDataSetChanged();

    }



    class GroupViewHolder {
        private final ImageView imagem;
        private final TextView item;

        GroupViewHolder(View view) {
            imagem = (ImageView) view.findViewById(R.id.icone);
            item = (TextView) view.findViewById(R.id.laptop);

        }
    }
    class CategoryViewHolder {
        private final TextView item;
        private final ImageView delete;
        private Button botaoAdicionar;

        CategoryViewHolder(View view) {
            this.item = (TextView) view.findViewById(R.id.laptop);
            this.delete = (ImageView) view.findViewById(R.id.delete);
            this.botaoAdicionar = (Button) view.findViewById(R.id.botao_adicionar);
        }
    }
}