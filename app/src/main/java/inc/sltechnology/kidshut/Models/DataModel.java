package inc.sltechnology.kidshut.Models;

public class DataModel {

    private String Title;
    private String Turl;
    private String TvId;

    public DataModel() {

    }

    public DataModel(String title, String turl, String tvId) {
        Title = title;
        Turl = turl;
        TvId = tvId;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTurl() {
        return Turl;
    }

    public void setTurl(String turl) {
        Turl = turl;
    }

    public String getTvId() {
        return TvId;
    }

    public void setTvId(String tvId) {
        TvId = tvId;
    }

}
