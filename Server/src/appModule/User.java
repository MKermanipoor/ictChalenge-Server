package appModule;



import java.util.Date;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class User {

    private long dbID;

    private String userName;
    private String userPassWord;
    private String token;


    //todo must be set time zone
    private Date lastAccessTime ;
    private Date createTime;




    public User(long dbID,  String userName, String userPassWord, String token,Date lastAccessTime, Date createTime) {
        this.dbID = dbID;
        setUserName(userName);
        setUserPassWord(userPassWord);
        setLastAccessTime(lastAccessTime);
        setCreateTime(createTime);
        setToken(token);

    }


    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }
    public long getDbID() {
        return dbID;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getCreateTime() {
        return createTime;
    }











}
