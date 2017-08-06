package hr.matvidako.lineupapp.napi;

import java.util.List;

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
    }

}
