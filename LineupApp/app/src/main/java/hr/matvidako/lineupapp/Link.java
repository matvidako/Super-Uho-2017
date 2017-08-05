package hr.matvidako.lineupapp;

import java.util.Arrays;

/**
 * Link object used by playlist etc.
 */
public class Link {
    public String[] ids;
    public String href;
    public String type;
    public String[] names;

    public String getFirstId() {
        if (ids != null && ids.length > 0) {
            return ids[0];
        } else {
            return "";
        }
    }

    public String debugString() {
        String sb = "Link{" + "ids=" + Arrays.toString(ids) +
                ", href='" + href + '\'' +
                ", type='" + type + '\'' +
                ", names=" + Arrays.toString(names) +
                '}';
        return sb;
    }
}
