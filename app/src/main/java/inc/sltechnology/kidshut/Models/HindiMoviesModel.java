package inc.sltechnology.kidshut.Models;

public class HindiMoviesModel {

    private String HMThumb;
    private String HMTitle;
    private String HMVideo;
    private String HMCast;
    private String HMCover;
    private String HMDesc;
    private String HMTLink;
    private String HMVLink;

    public HindiMoviesModel() {

    }

    public HindiMoviesModel(String HMCast, String HMCover, String HMDesc, String HMTLink, String HMVLink) {
        this.HMCast = HMCast;
        this.HMCover = HMCover;
        this.HMDesc = HMDesc;
        this.HMTLink = HMTLink;
        this.HMVLink = HMVLink;
    }

    public HindiMoviesModel(String HMThumb, String HMTitle, String HMVideo) {
        this.HMThumb = HMThumb;
        this.HMTitle = HMTitle;
        this.HMVideo = HMVideo;
    }

    public String getHMCast() {
        return HMCast;
    }

    public void setHMCast(String HMCast) {
        this.HMCast = HMCast;
    }

    public String getHMCover() {
        return HMCover;
    }

    public void setHMCover(String HMCover) {
        this.HMCover = HMCover;
    }

    public String getHMDesc() {
        return HMDesc;
    }

    public void setHMDesc(String HMDesc) {
        this.HMDesc = HMDesc;
    }

    public String getHMTLink() {
        return HMTLink;
    }

    public void setHMTLink(String HMTLink) {
        this.HMTLink = HMTLink;
    }

    public String getHMVLink() {
        return HMVLink;
    }

    public void setHMVLink(String HMVLink) {
        this.HMVLink = HMVLink;
    }

    public String getHMThumb() {
        return HMThumb;
    }

    public void setHMThumb(String HMThumb) {
        this.HMThumb = HMThumb;
    }

    public String getHMTitle() {
        return HMTitle;
    }

    public void setHMTitle(String HMTitle) {
        this.HMTitle = HMTitle;
    }

    public String getHMVideo() {
        return HMVideo;
    }

    public void setHMVideo(String HMVideo) {
        this.HMVideo = HMVideo;
    }
}
