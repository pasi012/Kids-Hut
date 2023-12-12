package inc.sltechnology.kidshut.Models;

public class SinhalaStoriesModel {

    private String SSThumb;
    private String SSTitle;
    private String SSVideo;
    private String SSCover;
    private String SSDesc;

    public SinhalaStoriesModel() {

    }

    public SinhalaStoriesModel(String SSCover, String SSDesc) {
        this.SSCover = SSCover;
        this.SSDesc = SSDesc;
    }

    public SinhalaStoriesModel(String SSThumb, String SSTitle, String SSVideo) {
        this.SSThumb = SSThumb;
        this.SSTitle = SSTitle;
        this.SSVideo = SSVideo;
    }

    public String getSSCover() {
        return SSCover;
    }

    public void setSSCover(String SSCover) {
        this.SSCover = SSCover;
    }

    public String getSSDesc() {
        return SSDesc;
    }

    public void setSSDesc(String SSDesc) {
        this.SSDesc = SSDesc;
    }

    public String getSSThumb() {
        return SSThumb;
    }

    public void setSSThumb(String SSThumb) {
        this.SSThumb = SSThumb;
    }

    public String getSSTitle() {
        return SSTitle;
    }

    public void setSSTitle(String SSTitle) {
        this.SSTitle = SSTitle;
    }

    public String getSSVideo() {
        return SSVideo;
    }

    public void setSSVideo(String SSVideo) {
        this.SSVideo = SSVideo;
    }
}
