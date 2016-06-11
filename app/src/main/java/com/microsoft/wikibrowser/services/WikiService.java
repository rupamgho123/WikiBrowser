package com.microsoft.wikibrowser.services;

import com.microsoft.wikibrowser.models.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rupam.ghosh on 11/06/16.
 */
public interface WikiService {
  @GET("w/api.php") Call<SearchResponse> search(@Query("action") String action,@Query("prop") String prop,
      @Query("format") String format,@Query("piprop") String piProp,@Query("pithumbsize") Integer piThumbSize,
      @Query("pilimit") Integer piLimit,@Query("generator") String generator,@Query("gpssearch") String gpsSearch);
}
