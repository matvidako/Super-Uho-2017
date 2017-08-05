package hr.matvidako.lineupapp;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NapiService {


    @GET("/v2/genres/{genre_id}/artists/top")
    Observable<ArtistsResponse> getGenreTopArtists(@Path("genre_id") String genreId,
                                                   @Query("limit") int limit,
                                                   @Query("offset") int offset,
                                                   @Query("catalog") String catalog,
                                                   @Query("range") String range);

    @GET("/v2.2/tags")
    Observable<TagsResponse> getAllTags(@Query("catalog") String catalog );

}
