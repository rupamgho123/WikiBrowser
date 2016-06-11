package com.microsoft.wikibrowser.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.microsoft.wikibrowser.R;
import com.microsoft.wikibrowser.models.Page;
import com.microsoft.wikibrowser.models.Pages;
import com.microsoft.wikibrowser.models.SearchResponse;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.WikiPageHolder> {

  SearchResponse searchResponse;
  List<Page> pages;
  Context context;
  public SearchListAdapter(SearchResponse searchResponse,Context context){
    this.searchResponse = searchResponse;
    this.context = context;
    pages = new ArrayList<>();
    if(searchResponse.getQuery() != null) {
      Pages query = searchResponse.getQuery();
      if(query.getPages() != null) {
        pages.addAll(query.getPages().values());
      }
    }
    if(pages.size() == 0){
      Toast.makeText(context,"No results from wikipedia",Toast.LENGTH_LONG).show();
    }
  }

  @Override public WikiPageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new WikiPageHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item, parent, false));
  }

  @Override public void onBindViewHolder(WikiPageHolder holder, int position) {
    Page page = pages.get(position);
    holder.titleView.setText(page.getTitle());
    if(page.getThumbnail() != null) {
      Picasso.with(context).
          load(page.getThumbnail().getSource()).
          placeholder(R.drawable.placeholder).
          into(holder.thumbnailView);
    }
    else {
      holder.thumbnailView.setImageResource(R.drawable.placeholder);
    }
  }

  @Override public int getItemCount() {
    return pages.size();
  }

  public static class WikiPageHolder extends RecyclerView.ViewHolder{
    ImageView thumbnailView;
    TextView titleView;

    public WikiPageHolder(View itemView) {
      super(itemView);
      titleView = (TextView) itemView.findViewById(R.id.title);
      thumbnailView = (ImageView) itemView.findViewById(R.id.imgView);
    }
  }
}
