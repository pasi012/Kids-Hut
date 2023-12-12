package inc.sltechnology.kidshut.Models;

public class JapaneseStoriesModel {

    private String JSThumb;
    private String JSTitle;
    private String JSVideo;
    private String JSCover;
    private String JSDesc;

    public JapaneseStoriesModel() {

    }

    public JapaneseStoriesModel(String JSCover, String JSDesc) {
        this.JSCover = JSCover;
        this.JSDesc = JSDesc;
    }

    public JapaneseStoriesModel(String JSThumb, String JSTitle, String JSVideo) {
        this.JSThumb = JSThumb;
        this.JSTitle = JSTitle;
        this.JSVideo = JSVideo;
    }

    public String getJSCover() {
        return JSCover;
    }

    public void setJSCover(String JSCover) {
        this.JSCover = JSCover;
    }

    public String getJSDesc() {
        return JSDesc;
    }

    public void setJSDesc(String JSDesc) {
        this.JSDesc = JSDesc;
    }

    public String getJSThumb() {
        return JSThumb;
    }

    public void setJSThumb(String JSThumb) {
        this.JSThumb = JSThumb;
    }

    public String getJSTitle() {
        return JSTitle;
    }

    public void setJSTitle(String JSTitle) {
        this.JSTitle = JSTitle;
    }

    public String getJSVideo() {
        return JSVideo;
    }

    public void setJSVideo(String JSVideo) {
        this.JSVideo = JSVideo;
    }
}
