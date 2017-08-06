package hr.matvidako.lineupapp.bands;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BandsInTown {

    private static BandsInTown instance;
    private BandsInTownService bandsService;
    private Scheduler observeScheduler = AndroidSchedulers.mainThread();
    private Scheduler subscribeScheduler = Schedulers.io();

    public static BandsInTown getInstance() {
        if(instance == null) {
            instance = new BandsInTown();
        }
        return instance;
    }

    private BandsInTown() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit baseApiRetrofit = new Retrofit.Builder()
                .baseUrl("http:/rest.bandsintown.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(createHttpClientWithInterceptor())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        bandsService = baseApiRetrofit.create(BandsInTownService.class);
    }

    private OkHttpClient createHttpClientWithInterceptor() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addNetworkInterceptor(loggingInterceptor);
        return clientBuilder.build();
    }

    public Observable<List<Gig>> getGigsForArtist(String artistName) {
        return bandsService.getArtistGigs(artistName, "lineup").subscribeOn(subscribeScheduler).observeOn(observeScheduler);
    }

}
