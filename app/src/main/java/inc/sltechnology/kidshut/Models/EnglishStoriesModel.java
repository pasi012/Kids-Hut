package inc.sltechnology.kidshut.Models;

public class EnglishStoriesModel {

    private String ESThumb;
    private String ESTitle;
    private String ESVideo;
    private String ESCover;
    private String ESDesc;

    public EnglishStoriesModel() {

    }

    public EnglishStoriesModel(String ESCover, String ESDesc) {
        this.ESCover = ESCover;
        this.ESDesc = ESDesc;
    }

    public EnglishStoriesModel(String ESThumb, String ESTitle, String ESVideo) {
        this.ESThumb = ESThumb;
        this.ESTitle = ESTitle;
        this.ESVideo = ESVideo;
    }

    public String getESCover() {
        return ESCover;
    }

    public void setESCover(String ESCover) {
        this.ESCover = ESCover;
    }

    public String getESDesc() {
        return ESDesc;
    }

    public void setESDesc(String ESDesc) {
        this.ESDesc = ESDesc;
    }

    public String getESThumb() {
        return ESThumb;
    }

    public void setESThumb(String ESThumb) {
        this.ESThumb = ESThumb;
    }

    public String getESTitle() {
        return ESTitle;
    }

    public void setESTitle(String ESTitle) {
        this.ESTitle = ESTitle;
    }

    public String getESVideo() {
        return ESVideo;
    }

    public void setESVideo(String ESVideo) {
        this.ESVideo = ESVideo;
    }
}
