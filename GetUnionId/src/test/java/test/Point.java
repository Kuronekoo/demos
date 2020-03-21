package test;

public class Point {
private String occudate;
    private String memberid;
    private String totalpoints;
    private String sheettypename;
    private String cardno;
    private String points;
    private String directflag;
    private String paymoney;
    private String serialid;

    public Point() {
    }

    public Point(String occudate, String memberid, String totalpoints, String sheettypename, String cardno, String points, String directflag, String paymoney, String serialid) {
        this.occudate = occudate;
        this.memberid = memberid;
        this.totalpoints = totalpoints;
        this.sheettypename = sheettypename;
        this.cardno = cardno;
        this.points = points;
        this.directflag = directflag;
        this.paymoney = paymoney;
        this.serialid = serialid;
    }

    public String getOccudate() {
        return occudate;
    }

    public void setOccudate(String occudate) {
        this.occudate = occudate;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(String totalpoints) {
        this.totalpoints = totalpoints;
    }

    public String getSheettypename() {
        return sheettypename;
    }

    public void setSheettypename(String sheettypename) {
        this.sheettypename = sheettypename;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDirectflag() {
        return directflag;
    }

    public void setDirectflag(String directflag) {
        this.directflag = directflag;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getSerialid() {
        return serialid;
    }

    public void setSerialid(String serialid) {
        this.serialid = serialid;
    }
}
