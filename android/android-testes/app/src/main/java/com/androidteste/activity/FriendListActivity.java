package com.androidteste.activity;


import android.app.Activity;
import android.app.Application;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.androidteste.R;
import com.androidteste.adapter.FriendListAdapter;
import com.androidteste.model.Friend;

import java.util.ArrayList;

/**
 * Display Contact list when sharing sensor
 *
 * @author eranga herath(erangeb@gmail.com)
 */
public class FriendListActivity extends Activity implements SearchView.OnQueryTextListener {

    private Application application;
    private ListView friendListView;
    private SearchView searchView;
    private MenuItem searchMenuItem;
    private FriendListAdapter friendListAdapter;
    private ArrayList<Friend> friendList;

    /**
     * {@inheritDoc}
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_list);
        application =  this.getApplication();

        setActionBar();
        initFriendList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FriendListActivity.this.finish();
//                FriendListActivity.this.overridePendingTransition(R.anim.stay_in, R.anim.bottom_out);
//                ActivityUtils.hideSoftKeyboard(this);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set action bar
     *      1. properties
     *      2. title with custom font
     */
    private void setActionBar() {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("Friends");

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/vegur_2.otf");
        int titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTitle = (TextView) (this.findViewById(titleId));
        actionBarTitle.setTextColor(Color.WHITE);
        actionBarTitle.setTypeface(typeface);
    }

    /**
     * Initialize friend list
     */
    private void initFriendList() {
//        friendList = application.getContactList();
        friendList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            friendList.add(new Friend("Amigo #"  + i, "9999-999" + i));
        }
        friendListView = (ListView) findViewById(R.id.friend_list);
        friendListAdapter = new FriendListAdapter(this, friendList);

        // add header and footer for list
//        View headerView = View.inflate(this, R.layout.list_header, null);
//        View footerView = View.inflate(this, R.layout.list_header, null);
//        friendListView.addHeaderView(headerView);
//        friendListView.addFooterView(footerView);
        friendListView.setAdapter(friendListAdapter);
        friendListView.setTextFilterEnabled(false);

        // use to enable search view popup text
        //friendListView.setTextFilterEnabled(true);

        // set up click listener
//        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position>0 && position <= friendList.size()) {
//                    handelListItemClick((Friend)friendListAdapter.getItem(position - 1));
//                }
//            }
//        });
    }

    /**
     * Navigate to share activity form here
     * @param user user
     */
//    private void handelListItemClick(Friend user) {
//        // close search view if its visible
//        if (searchView.isShown()) {
//            searchMenuItem.collapseActionView();
//            searchView.setQuery("", false);
//        }
//
//        // pass selected user and sensor to share activity
//        Intent intent = new Intent(this, ShareActivity.class);
//        intent.putExtra("com.score.senzors.pojos.User", user);
//        intent.putExtra("com.score.senzors.pojos.Sensor", application.getCurrentSensor());
//        this.startActivity(intent);
//        this.overridePendingTransition(R.anim.right_in, R.anim.stay_in);
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        this.overridePendingTransition(R.anim.stay_in, R.anim.bottom_out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        friendListAdapter.getFilter().filter(newText);

        // use to enable search view popup text
//        if (TextUtils.isEmpty(newText)) {
//            friendListView.clearTextFilter();
//        }
//        else {
//            friendListView.setFilterText(newText.toString());
//        }

        return true;
    }
}