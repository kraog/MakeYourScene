package io.github.epelde.crispychainsaw;

import android.preference.PreferenceActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;


/**
 * Created by Gorka on 19/04/2016.
 */
public class RecyclerViewIpsumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    String header;
    private List<String> items;

    public RecyclerViewIpsumAdapter(List<String> items) {
        this.items = items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType==TYPE_ITEM){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ipsum, parent, false);
            v.setOnClickListener(this);
            return new ViewHolder(v);
        } else{
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_container,parent,false);
            return new HeaderViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       if(holder instanceof ViewHolder) {
           ViewHolder vh = (ViewHolder)holder;
           String item = items.get(position-1);
           vh.text.setText(item);
       }
        else if(holder instanceof HeaderViewHolder) {
           HeaderViewHolder vh = (HeaderViewHolder)holder;

       }
    }

    @Override
    public int getItemCount() {
        return items.size()+1;
    }

    @Override
    public void onClick(final View v) {
    }

    private boolean isPositionHeader(int position)
    {
        return position == 0;
    }
    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.title_ipsum);
        }
    }

    protected static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}

