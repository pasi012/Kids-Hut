package inc.sltechnology.kidshut.Models;

public class SinhalaMoviesModel {

    private String SMThumb;
    private String SMTitle;
    private String SMVideo;
    private String SMCast;
    private String SMCover;
    private String SMDesc;
    private String SMTLink;
    private String SMVLink;

    public SinhalaMoviesModel() {

    }

    public SinhalaMoviesModel(String SMCast, String SMCover, String SMDesc, String SMTLink, String SMVLink) {
        this.SMCast = SMCast;
        this.SMCover = SMCover;
        this.SMDesc = SMDesc;
        this.SMTLink = SMTLink;
        this.SMVLink = SMVLink;
    }

    public SinhalaMoviesModel(String SMThumb, String SMTitle, String SMVideo) {
        this.SMThumb = SMThumb;
        this.SMTitle = SMTitle;
        this.SMVideo = SMVideo;
    }

    public String getSMCast() {
        return SMCast;
    }

    public void setSMCast(String SMCast) {
        this.SMCast = SMCast;
    }

    public String getSMCover() {
        return SMCover;
    }

    public void setSMCover(String SMCover) {
        this.SMCover = SMCover;
    }

    public String getSMDesc() {
        return SMDesc;
    }

    public void setSMDesc(String SMDesc) {
        this.SMDesc = SMDesc;
    }

    public String getSMTLink() {
        return SMTLink;
    }

    public void setSMTLink(String SMTLink) {
        this.SMTLink = SMTLink;
    }

    public String getSMVLink() {
        return SMVLink;
    }

    public void setSMVLink(String SMVLink) {
        this.SMVLink = SMVLink;
    }

    public String getSMThumb() {
        return SMThumb;
    }

    public void setSMThumb(String SMThumb) {
        this.SMThumb = SMThumb;
    }

    public String getSMTitle() {
        return SMTitle;
    }

    public void setSMTitle(String SMTitle) {
        this.SMTitle = SMTitle;
    }

    public String getSMVideo() {
        return SMVideo;
    }

    public void setSMVideo(String SMVideo) {
        this.SMVideo = SMVideo;
    }
}
