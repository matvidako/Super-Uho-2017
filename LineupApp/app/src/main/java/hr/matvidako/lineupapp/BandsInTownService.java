package hr.matvidako.lineupapp;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BandsInTownService {

    @GET("/artists/{artist_name}/events")
    Observable<List<Gig>> getArtistGigs(@Path("artist_name") String artistName, @Query("app_id") String appId);

}
