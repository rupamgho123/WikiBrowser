package com.microsoft.wikibrowser.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.microsoft.wikibrowser.events.SearchQueryRequestedEvent;
import com.microsoft.wikibrowser.models.SearchResponse;
import com.microsoft.wikibrowser.providers.WikiServiceProvider;
import com.squareup.otto.Subscribe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.microsoft.wikibrowser.R;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public class SearchListFragment extends BaseFragment implements Callback<SearchResponse>{
  WikiServiceProvider wikiServiceProvider = new WikiServiceProvider();

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.list,container,false);
  }

  @Subscribe
  public void onSearchQueryRequested(SearchQueryRequestedEvent event){
    Call<SearchResponse> searchCall = wikiServiceProvider.get().search("query","pageimages","json",
        "thumbnail",50,50,"prefixsearch",event.getQuery());
    searchCall.enqueue(this);
  }

  @Override public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

  }

  @Override public void onFailure(Call<SearchResponse> call, Throwable t) {
    Toast.makeText(getContext(),"Could not fetch data from wikipedia",Toast.LENGTH_SHORT).show();
  }
}
