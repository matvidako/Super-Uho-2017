package hr.matvidako.lineupapp.napi;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TagsResponse {

    @SerializedName("tags")
    public List<Tag> tags;

    public static class Tag {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("isProtected")
        public boolean isProtected;
        @SerializedName("parentId")
        public String parentId;
        @SerializedName("genreId")
        public String genreId;
        @SerializedName("childIds")
        public List<String> childIds;
        @SerializedName("contentId")
        public String contentId;
    }

    public List<Genre> getGenres(List<String> blacklistedGenreIds) {
        List<Genre> genres = new ArrayList<>();
        for(Tag tag : tags) {
            if(!TextUtils.isEmpty(tag.genreId) && !blacklistedGenreIds.contains(tag.genreId)) {
                genres.add(new Genre(tag.genreId, tag.name));
            }
        }
        return genres;
    }

    public class Genre {
        public final String id;
        public final String name;

        public Genre(String id, String name) {

            this.id = id;
            this.name = name;
        }
    }

}
