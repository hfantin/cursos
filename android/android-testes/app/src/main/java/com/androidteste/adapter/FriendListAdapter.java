package com.androidteste.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.androidteste.R;
import com.androidteste.activity.FriendListActivity;
import com.androidteste.model.Friend;

import java.util.ArrayList;

/**
 * Display friend list
 *
 */
public class FriendListAdapter extends BaseAdapter implements Filterable {

    private FriendListActivity activity;
    private FriendFilter friendFilter;
    private ArrayList<Friend> friendList;
    private ArrayList<Friend> filteredList;

    /**
     * Initialize context variables
     * @param activity friend list activity
     * @param friendList friend list
     */
    public FriendListAdapter(FriendListActivity activity, ArrayList<Friend> friendList) {
        this.activity = activity;
        this.friendList = friendList;
        this.filteredList = friendList;
        getFilter();
    }

    /**
     * Get size of user list
     * @return userList size
     */
    @Override
    public int getCount() {
        return filteredList.size();
    }

    /**
     * Get specific item from user list
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    /**
     * Get user list item id
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Create list row view
     * @param position index
     * @param view current list item view
     * @param parent parent
     * @return view
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        final ViewHolder holder;
        final Friend friend = (Friend) getItem(position);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.friends_adapter, parent, false);
            holder = new ViewHolder();
            holder.nome = (TextView) view.findViewById(R.id.friend_nome);
            holder.telefone = (TextView) view.findViewById(R.id.friend_telefone);
            view.setTag(holder);
        } else {
            // get view holder back
            holder = (ViewHolder) view.getTag();
        }

        // bind text with view holder content view for efficient use
        holder.nome.setText(friend.getNome());
        holder.telefone.setText(friend.getNome());

        return view;
    }

    /**
     * Get custom filter
     * @return filter
     */
    @Override
    public Filter getFilter() {
        if (friendFilter == null) {
            friendFilter = new FriendFilter();
        }

        return friendFilter;
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        TextView nome;
        TextView telefone;
    }

    /**
     * Custom filter for friend list
     * Filter content in friend list according to the search text
     */
    private class FriendFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if (constraint!=null && constraint.length()>0) {
                ArrayList<Friend> tempList = new ArrayList<Friend>();

                // search content in friend list
                for (Friend friend : friendList) {
                    if (friend.getNome().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        tempList.add(friend);
                    }
                }
                filterResults.count = tempList.size();
                filterResults.values = tempList;
            } else {
                filterResults.count = friendList.size();
                filterResults.values = friendList;
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Friend>) results.values;
            notifyDataSetChanged();
        }
    }

}
