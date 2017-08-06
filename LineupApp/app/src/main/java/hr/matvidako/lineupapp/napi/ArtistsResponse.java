package hr.matvidako.lineupapp.napi;

import java.util.List;
import java.util.Random;

/*
ArtistMetadata is a simple pojo for receiving JSON responses to the getArtistMetadata napi call.
 */
public class ArtistsResponse {

    public List<Artist> artists;

    public Artist first() {
        if (artists != null && artists.size() > 0) {
            return artists.get(0);
        }
        return null;
    }

    public static class Artist {
        public String id;
        public String name;
        public int cost;

        public Links links;

        public static class Links {
            public Link albums;
            public Link images;
            public Link posts;
            public Link topTracks;
            public Link contemporaries;
            public Link followers;
            public Link albumsSinglesAndEPs;
            public Link albumsCompilations;
            public Link albumsMain;
            public Link genres;
            public Link stations;
            public Link influences;
            public Link relatedProjects;
        }

        public void setCost(int chartPosition) {
            cost = 1000000 / chartPosition;
        }

        public void setCostForSimilarArtist(int otherCost) {
            Random random = new Random();
            int costDiff = random.nextInt(otherCost / 50);
            boolean plus = random.nextBoolean();
            if(plus) {
                cost = otherCost + costDiff;
            } else {
                cost = otherCost - costDiff;
            }
        }


        public void setCostForFollowerArtist(int otherCost) {
            Random random = new Random();
            int costDiff = random.nextInt(otherCost / 50);
            boolean plus = random.nextBoolean();
            int multiplier = 5;
            if(plus) {
                cost = otherCost / multiplier + costDiff;
            } else {
                cost = otherCost / multiplier - costDiff;
            }
        }
    }

}
