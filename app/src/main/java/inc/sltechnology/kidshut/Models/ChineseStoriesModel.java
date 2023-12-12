package inc.sltechnology.kidshut.Models;

public class ChineseStoriesModel {

    private String CSThumb;
    private String CSTitle;
    private String CSVideo;
    private String CSCover;
    private String CSDesc;

    public ChineseStoriesModel() {

    }

    public ChineseStoriesModel(String CSCover, String CSDesc) {
        this.CSCover = CSCover;
        this.CSDesc = CSDesc;
    }

    public ChineseStoriesModel(String CSThumb, String CSTitle, String CSVideo) {
        this.CSThumb = CSThumb;
        this.CSTitle = CSTitle;
        this.CSVideo = CSVideo;
    }

    public String getCSCover() {
        return CSCover;
    }

    public void setCSCover(String CSCover) {
        this.CSCover = CSCover;
    }

    public String getCSDesc() {
        return CSDesc;
    }

    public void setCSDesc(String CSDesc) {
        this.CSDesc = CSDesc;
    }

    public String getCSThumb() {
        return CSThumb;
    }

    public void setCSThumb(String CSThumb) {
        this.CSThumb = CSThumb;
    }

    public String getCSTitle() {
        return CSTitle;
    }

    public void setCSTitle(String CSTitle) {
        this.CSTitle = CSTitle;
    }

    public String getCSVideo() {
        return CSVideo;
    }

    public void setCSVideo(String CSVideo) {
        this.CSVideo = CSVideo;
    }
}
