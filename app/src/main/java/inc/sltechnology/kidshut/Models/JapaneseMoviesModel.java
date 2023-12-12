package inc.sltechnology.kidshut.Models;

public class JapaneseMoviesModel {

    private String JMThumb;
    private String JMTitle;
    private String JMVideo;
    private String JMCast;
    private String JMCover;
    private String JMDesc;
    private String JMTLink;
    private String JMVLink;

    public JapaneseMoviesModel() {

    }

    public JapaneseMoviesModel(String JMCast, String JMCover, String JMDesc, String JMTLink, String JMVLink) {
        this.JMCast = JMCast;
        this.JMCover = JMCover;
        this.JMDesc = JMDesc;
        this.JMTLink = JMTLink;
        this.JMVLink = JMVLink;
    }

    public JapaneseMoviesModel(String JMThumb, String JMTitle, String JMVideo) {
        this.JMThumb = JMThumb;
        this.JMTitle = JMTitle;
        this.JMVideo = JMVideo;
    }

    public String getJMCast() {
        return JMCast;
    }

    public void setJMCast(String JMCast) {
        this.JMCast = JMCast;
    }

    public String getJMCover() {
        return JMCover;
    }

    public void setJMCover(String JMCover) {
        this.JMCover = JMCover;
    }

    public String getJMDesc() {
        return JMDesc;
    }

    public void setJMDesc(String JMDesc) {
        this.JMDesc = JMDesc;
    }

    public String getJMTLink() {
        return JMTLink;
    }

    public void setJMTLink(String JMTLink) {
        this.JMTLink = JMTLink;
    }

    public String getJMVLink() {
        return JMVLink;
    }

    public void setJMVLink(String JMVLink) {
        this.JMVLink = JMVLink;
    }

    public String getJMThumb() {
        return JMThumb;
    }

    public void setJMThumb(String JMThumb) {
        this.JMThumb = JMThumb;
    }

    public String getJMTitle() {
        return JMTitle;
    }

    public void setJMTitle(String JMTitle) {
        this.JMTitle = JMTitle;
    }

    public String getJMVideo() {
        return JMVideo;
    }

    public void setJMVideo(String JMVideo) {
        this.JMVideo = JMVideo;
    }
}
