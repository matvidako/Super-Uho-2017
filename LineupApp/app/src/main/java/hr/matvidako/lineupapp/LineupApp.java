package hr.matvidako.lineupapp;


import android.app.Application;

import hr.matvidako.lineupapp.bands.BandsInTown;
import hr.matvidako.lineupapp.napi.Napi;

public class LineupApp extends Application {

    public static final String CATALOG = "US";
    private static LineupApp instance;
    private Napi napi;
    private BandsInTown bandsInTown;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        napi = Napi.getInstance();
        bandsInTown = BandsInTown.getInstance();
    }

    public static LineupApp getInstance() {
        return instance;
    }

    public Napi getNapi() {
        return napi;
    }

    public BandsInTown getBandsInTown() {
        return bandsInTown;
    }

}
