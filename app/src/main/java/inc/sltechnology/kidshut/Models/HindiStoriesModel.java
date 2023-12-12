package inc.sltechnology.kidshut.Models;

public class HindiStoriesModel {

    private String HSThumb;
    private String HSTitle;
    private String HSVideo;
    private String HSCover;
    private String HSDesc;

    public HindiStoriesModel() {

    }

    public HindiStoriesModel(String HSCover, String HSDesc) {
        this.HSCover = HSCover;
        this.HSDesc = HSDesc;
    }

    public HindiStoriesModel(String HSThumb, String HSTitle, String HSVideo) {
        this.HSThumb = HSThumb;
        this.HSTitle = HSTitle;
        this.HSVideo = HSVideo;
    }

    public String getHSCover() {
        return HSCover;
    }

    public void setHSCover(String HSCover) {
        this.HSCover = HSCover;
    }

    public String getHSDesc() {
        return HSDesc;
    }

    public void setHSDesc(String HSDesc) {
        this.HSDesc = HSDesc;
    }

    public String getHSThumb() {
        return HSThumb;
    }

    public void setHSThumb(String HSThumb) {
        this.HSThumb = HSThumb;
    }

    public String getHSTitle() {
        return HSTitle;
    }

    public void setHSTitle(String HSTitle) {
        this.HSTitle = HSTitle;
    }

    public String getHSVideo() {
        return HSVideo;
    }

    public void setHSVideo(String HSVideo) {
        this.HSVideo = HSVideo;
    }
}
