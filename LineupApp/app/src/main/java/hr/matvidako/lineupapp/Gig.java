package hr.matvidako.lineupapp;


import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Gig {

    @SerializedName("datetime")
    Date date;

    public static boolean isAvailable(List<Gig> gigs, Date festivalStart, Date festivalEnd) {
        for(Gig gig : gigs) {
            if(gig.date.before(festivalEnd) && gig.date.after(festivalStart)) {
                return false;
            }
        }
        return true;
    }

}
