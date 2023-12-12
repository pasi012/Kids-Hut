package inc.sltechnology.kidshut.Models;

public class TamilMoviesModel {

    private String TMThumb;
    private String TMTitle;
    private String TMVideo;
    private String TMCast;
    private String TMCover;
    private String TMDesc;
    private String TMTLink;
    private String TMVLink;

    public TamilMoviesModel() {

    }

    public TamilMoviesModel(String TMVLink) {
        this.TMVLink = TMVLink;
    }

    public TamilMoviesModel(String TMCast, String TMCover, String TMDesc, String TMTLink) {
        this.TMCast = TMCast;
        this.TMCover = TMCover;
        this.TMDesc = TMDesc;
        this.TMTLink = TMTLink;
    }

    public TamilMoviesModel(String TMThumb, String TMTitle, String TMVideo) {
        this.TMThumb = TMThumb;
        this.TMTitle = TMTitle;
        this.TMVideo = TMVideo;
    }

    public String getTMVLink() {
        return TMVLink;
    }

    public void setTMVLink(String TMVLink) {
        this.TMVLink = TMVLink;
    }

    public String getTMCast() {
        return TMCast;
    }

    public void setTMCast(String TMCast) {
        this.TMCast = TMCast;
    }

    public String getTMCover() {
        return TMCover;
    }

    public void setTMCover(String TMCover) {
        this.TMCover = TMCover;
    }

    public String getTMDesc() {
        return TMDesc;
    }

    public void setTMDesc(String TMDesc) {
        this.TMDesc = TMDesc;
    }

    public String getTMTLink() {
        return TMTLink;
    }

    public void setTMTLink(String TMTLink) {
        this.TMTLink = TMTLink;
    }

    public String getTMThumb() {
        return TMThumb;
    }

    public void setTMThumb(String TMThumb) {
        this.TMThumb = TMThumb;
    }

    public String getTMTitle() {
        return TMTitle;
    }

    public void setTMTitle(String TMTitle) {
        this.TMTitle = TMTitle;
    }

    public String getTMVideo() {
        return TMVideo;
    }

    public void setTMVideo(String TMVideo) {
        this.TMVideo = TMVideo;
    }
}
