package com.androidteste.activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.Toast;

import com.androidteste.R;
import com.androidteste.adapter.ExpandableListAdapter;
import com.androidteste.model.Grupo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpandableListActivity extends Activity implements
        SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private List<String> childList;
    private List<Grupo> grupos;
    private ExpandableListView expListView;
    private SearchView search;
    private ExpandableListAdapter expListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_list);
        createGroupList();
        expListView = (ExpandableListView) findViewById(R.id.laptop_list);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        search.setFocusable(false);

        expListAdapter = new ExpandableListAdapter(this, grupos);
        expListView.setAdapter(expListAdapter);
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.header, expListView, false);
        expListView.addHeaderView(header);
        expandAll();
        setGroupIndicatorToRight();

        // fixa
//        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
//                return true;
//            }
//        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
    }

    private void createGroupList() {
        grupos = new ArrayList<>();
        grupos.add(new Grupo(1, "Casa", Arrays.asList(new String[] {"Aluguel", "Animais", "Condominio"})));
        grupos.add(new Grupo(2, "Educacao", Arrays.asList(new String[] {"Colegio", "Cursos", "Faculdade", "Idiomas"})));
        grupos.add(new Grupo(3, "Transporte", Arrays.asList(new String[] {"Combustivel", "Transporte Publico", "Uber"})));
        grupos.add(new Grupo(4, "Outros", Arrays.asList(new String[] {"Cerveja", "Torresminho", "Pinga"})));
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<String>();
        for (String model : laptopModels)
            childList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


    private void expandAll() {
        // expande todos
        Log.i(this.getClass().getSimpleName(), this.getClass().getSimpleName() + " - total de linhas " + expListAdapter.getGroupCount());
        for(int i=0; i < expListAdapter.getGroupCount(); i++) {
            expListView.expandGroup(i);
        }
    }

    @Override
    public boolean onClose() {
        expListAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        expListAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        expListAdapter.filterData(query);
        expandAll();
        return false;
    }
}
