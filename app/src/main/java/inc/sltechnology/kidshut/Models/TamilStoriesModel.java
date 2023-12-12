package inc.sltechnology.kidshut.Models;

public class TamilStoriesModel {

    private String TSThumb;
    private String TSTitle;
    private String TSVideo;
    private String TSCover;
    private String TSDesc;

    public TamilStoriesModel() {

    }

    public TamilStoriesModel(String TSCover, String TSDesc) {
        this.TSCover = TSCover;
        this.TSDesc = TSDesc;
    }

    public TamilStoriesModel(String TSThumb, String TSTitle, String TSVideo) {
        this.TSThumb = TSThumb;
        this.TSTitle = TSTitle;
        this.TSVideo = TSVideo;
    }

    public String getTSCover() {
        return TSCover;
    }

    public void setTSCover(String TSCover) {
        this.TSCover = TSCover;
    }

    public String getTSDesc() {
        return TSDesc;
    }

    public void setTSDesc(String TSDesc) {
        this.TSDesc = TSDesc;
    }

    public String getTSThumb() {
        return TSThumb;
    }

    public void setTSThumb(String TSThumb) {
        this.TSThumb = TSThumb;
    }

    public String getTSTitle() {
        return TSTitle;
    }

    public void setTSTitle(String TSTitle) {
        this.TSTitle = TSTitle;
    }

    public String getTSVideo() {
        return TSVideo;
    }

    public void setTSVideo(String TSVideo) {
        this.TSVideo = TSVideo;
    }
}
