package inc.sltechnology.kidshut.Models;

public class ChineseMoviesModel {

    private String CMThumb;
    private String CMTitle;
    private String CMVideo;
    private String CMCast;
    private String CMCover;
    private String CMDesc;
    private String CMTLink;
    private String CMVLink;

    public ChineseMoviesModel() {

    }

    public ChineseMoviesModel(String CMCast, String CMCover, String CMDesc, String CMTLink, String CMVLink) {
        this.CMCast = CMCast;
        this.CMCover = CMCover;
        this.CMDesc = CMDesc;
        this.CMTLink = CMTLink;
        this.CMVLink = CMVLink;
    }

    public ChineseMoviesModel(String CMThumb, String CMTitle, String CMVideo) {
        this.CMThumb = CMThumb;
        this.CMTitle = CMTitle;
        this.CMVideo = CMVideo;
    }

    public String getCMCast() {
        return CMCast;
    }

    public void setCMCast(String CMCast) {
        this.CMCast = CMCast;
    }

    public String getCMCover() {
        return CMCover;
    }

    public void setCMCover(String CMCover) {
        this.CMCover = CMCover;
    }

    public String getCMDesc() {
        return CMDesc;
    }

    public void setCMDesc(String CMDesc) {
        this.CMDesc = CMDesc;
    }

    public String getCMTLink() {
        return CMTLink;
    }

    public void setCMTLink(String CMTLink) {
        this.CMTLink = CMTLink;
    }

    public String getCMVLink() {
        return CMVLink;
    }

    public void setCMVLink(String CMVLink) {
        this.CMVLink = CMVLink;
    }

    public String getCMThumb() {
        return CMThumb;
    }

    public void setCMThumb(String CMThumb) {
        this.CMThumb = CMThumb;
    }

    public String getCMTitle() {
        return CMTitle;
    }

    public void setCMTitle(String CMTitle) {
        this.CMTitle = CMTitle;
    }

    public String getCMVideo() {
        return CMVideo;
    }

    public void setCMVideo(String CMVideo) {
        this.CMVideo = CMVideo;
    }
}
