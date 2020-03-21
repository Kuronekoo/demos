package cn.com.crv.dp.tiger.sc;

public class OldUser {
    private String openid;
    private String lang;
    private int channel;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public OldUser(String openid, String lang) {
        this.openid = openid;
        this.lang = lang;
    }

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}
    
}
