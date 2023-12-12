package inc.sltechnology.kidshut.Models;

public class EnglishMoviesModel {

    private String EMThumb;
    private String EMTitle;
    private String EMVideo;
    private String EMCast;
    private String EMCover;
    private String EMDesc;
    private String EMTLink;
    private String EMVLink;
    private String Member;

    public EnglishMoviesModel() {

    }

    public EnglishMoviesModel(String EMVLink, String Member) {
        this.EMVLink = EMVLink;
        this.Member = Member;
    }

    public EnglishMoviesModel(String EMCast, String EMCover, String EMDesc, String EMTLink) {
        this.EMCast = EMCast;
        this.EMCover = EMCover;
        this.EMDesc = EMDesc;
        this.EMTLink = EMTLink;
    }

    public EnglishMoviesModel(String EMThumb, String EMTitle, String EMVideo) {
        this.EMThumb = EMThumb;
        this.EMTitle = EMTitle;
        this.EMVideo = EMVideo;
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public String getEMVLink() {
        return EMVLink;
    }

    public void setEMVLink(String EMVLink) {
        this.EMVLink = EMVLink;
    }

    public String getEMCast() {
        return EMCast;
    }

    public void setEMCast(String EMCast) {
        this.EMCast = EMCast;
    }

    public String getEMCover() {
        return EMCover;
    }

    public void setEMCover(String EMCover) {
        this.EMCover = EMCover;
    }

    public String getEMDesc() {
        return EMDesc;
    }

    public void setEMDesc(String EMDesc) {
        this.EMDesc = EMDesc;
    }

    public String getEMTLink() {
        return EMTLink;
    }

    public void setEMTLink(String EMTLink) {
        this.EMTLink = EMTLink;
    }

    public String getEMThumb() {
        return EMThumb;
    }

    public void setEMThumb(String EMThumb) {
        this.EMThumb = EMThumb;
    }

    public String getEMTitle() {
        return EMTitle;
    }

    public void setEMTitle(String EMTitle) {
        this.EMTitle = EMTitle;
    }

    public String getEMVideo() {
        return EMVideo;
    }

    public void setEMVideo(String EMVideo) {
        this.EMVideo = EMVideo;
    }
}
