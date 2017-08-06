package hr.matvidako.lineupapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    Napi napi;
    List<String> blacklistedGenreIds = Arrays.asList(new String[]{"g.120", "g.21", "g.197", "g.8223", "g.18", "g.4", "g.48", "g.304"});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        napi = LineupApp.getInstance().getNapi();
        napi.getALlTags(LineupApp.CATALOG).subscribe(new Subscriber<TagsResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(TagsResponse tagsResponse) {
                List<TagsResponse.Genre> genres = tagsResponse.getGenres(blacklistedGenreIds);

                for(TagsResponse.Genre genre : genres) {
                    //Log.d("DISI", genre.name + " " + genre.id);
                }

                napi.getTopArtistsForGenre(genres.get(2).id, LineupApp.CATALOG, 10).subscribe(new Subscriber<ArtistsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ArtistsResponse artistsResponse) {
                    }
                });
            }
        });

        LineupApp.getInstance().getBandsInTown().getGigsForArtist("Destroyer").subscribe(new Subscriber<List<Gig>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.d("DISI", e.toString());
            }

            @Override
            public void onNext(List<Gig> gigs) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date startDate = sdf.parse("28/11/2017");
                    Date enddDate = sdf.parse("30/11/2017");

                    Log.d("DISI", "available?" + Gig.isAvailable(gigs, startDate, enddDate));
                    startDate = sdf.parse("28/11/2018");
                    enddDate = sdf.parse("30/11/2018");
                    
                    Log.d("DISI", "available?" + Gig.isAvailable(gigs, startDate, enddDate));


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
