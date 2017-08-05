package hr.matvidako.lineupapp;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Napi {

    private static Napi instance;
    private NapiService napiService;
    private Scheduler observeScheduler = AndroidSchedulers.mainThread();
    private Scheduler subscribeScheduler = Schedulers.io();

    public static Napi getInstance() {
        if(instance == null) {
            instance = new Napi();
        }
        return instance;
    }

    private Napi() {
        Retrofit baseApiRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.napster.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(createHttpClientWithInterceptor())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        napiService = baseApiRetrofit.create(NapiService.class);
    }

    private OkHttpClient createHttpClientWithInterceptor() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addNetworkInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().
                        addQueryParameter(QueryKey.format, QueryValue.format).
                        addQueryParameter(QueryKey.apiKey, QueryValue.apiKey).
                        build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        });
        return clientBuilder.build();
    }

    public Observable<TagsResponse> getALlTags(String catalog) {
        return napiService.getAllTags(catalog).subscribeOn(subscribeScheduler).observeOn(observeScheduler);
    }

    public Observable<ArtistsResponse> getTopArtistsForGenre(String genreId, String catalog, int limit) {
        return napiService.getGenreTopArtists(genreId, limit, 0, catalog, TimeRange.YEAR.toString()).subscribeOn(subscribeScheduler).observeOn(observeScheduler);
    }

    private interface QueryValue {
        String format = "json";
        String apiKey = "ZWUxYjEwNDMtOWRmMi00YTQxLWEyY2YtZmIyMTg3M2YwMzgy";
    }

    private interface QueryKey {
        String format = "format";
        String apiKey = "apikey";
    }

}
