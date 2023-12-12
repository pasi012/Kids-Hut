package inc.sltechnology.kidshut.Models;

public class castModel {

    String cname;
    String curl;

    public castModel() {

    }

    public castModel(String cname, String curl) {
        this.cname = cname;
        this.curl = curl;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCurl() {
        return curl;
    }

    public void setCurl(String curl) {
        this.curl = curl;
    }
}
