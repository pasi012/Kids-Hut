package inc.sltechnology.kidshut.Models;

import com.google.firebase.database.ServerValue;

public class CommentModel {

        private String content;
        private String uid;
        private String uName;

        private Object timestamp;

    public CommentModel() {

    }

    public CommentModel(String content, String uid, String uName) {
        this.content = content;
        this.uid = uid;

        this.uName = uName;

        this.timestamp = ServerValue.TIMESTAMP;

    }

    public CommentModel(String content, String uid, String uName, Object timestamp) {
        this.content = content;
        this.uid = uid;

        this.uName = uName;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
