package hr.matvidako.lineupapp.napi;

public enum TimeRange {

    WEEK("week"),
    MONTH("month"),
    YEAR("year"),
    LIFE("life");

    public final String name;

    TimeRange(String name) {
        this.name = name;
    }

    public static TimeRange getDefaultForUserCharts() {
        return MONTH;
    }

    public static TimeRange getDefaultForGlobalCharts() {
        return WEEK;
    }

    @Override
    public String toString() {
        return name;
    }

}
