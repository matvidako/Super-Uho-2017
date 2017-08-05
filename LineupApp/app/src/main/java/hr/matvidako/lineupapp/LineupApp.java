package hr.matvidako.lineupapp;


import android.app.Application;

public class LineupApp extends Application {

    public static final String CATALOG = "IE";
    private static LineupApp instance;
    private Napi napi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        napi = Napi.getInstance();
    }

    public static LineupApp getInstance() {
        return instance;
    }

    public Napi getNapi() {
        return napi;
    }

}
