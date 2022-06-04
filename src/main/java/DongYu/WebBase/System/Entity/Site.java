package DongYu.WebBase.System.Entity;

import org.springframework.util.DigestUtils;

import java.util.Date;

public class Site {
    private String uri;
    private String name;
    private String passWord;
    private String caiId;
    private String LCode;
    private String cookes;
    private Date cookesTime;
    private String doUrl;


    public Site(String uri, String name, String passWord, String LCode, String caiId) {
        this.uri = uri;
        this.name = name;
        this.passWord = passWord;
        this.LCode = LCode;
        this.caiId = caiId;
        this.cookes = "";
        this.cookesTime = null;
    }

    public Site(String uri, String name, String passWord, String LCode, String caiId, String doUrl) {
        this.uri = uri;
        this.name = name;
        this.passWord = passWord;
        this.LCode = LCode;
        this.caiId = caiId;
        this.cookes = "";
        this.cookesTime = null;
        this.doUrl = doUrl;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCaiId() {
        return caiId;
    }

    public void setCaiId(String caiId) {
        this.caiId = caiId;
    }

    public String getLCode() {
        return LCode;
    }

    public void setLCode(String lCode) {
        LCode = lCode;
    }

    public String getCookes() {
        return cookes;
    }

    public void setCookes(String cookes) {
        this.cookes = cookes;
    }

    public Date getCookesTime() {
        return cookesTime;
    }

    public void setCookesTime(Date cookesTime) {
        this.cookesTime = cookesTime;
    }

    public String getDoUrl() {
        return doUrl;
    }

    public void setDoUrl(String doUrl) {
        this.doUrl = doUrl;
    }

    public String getMd5Name() {
        String str = this.LCode + this.name + "{0}";
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }

    public String getMd5PassWord() {
        return DigestUtils.md5DigestAsHex(this.passWord.getBytes());
    }

}
