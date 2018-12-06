package appModule;

import java.util.Date;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class UserFile {

    private long dbID;
    private long userID;
    private long fileID;
    private long accessLevel;
    private String url;
    private Date createTime;

    public UserFile(long dbID, long userID, long fileID, long accessLevel, String url, Date createTime) {
        setDbID(dbID);
        setUserID(userID);
        setFileID(fileID);
        setAccessLevel(accessLevel);
        setUrl(url);
        setCreateTime(createTime);
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setFileID(long fileID) {
        this.fileID = fileID;
    }

    public void setAccessLevel(long accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getDbID() {
        return dbID;
    }

    public long getUserID() {
        return userID;
    }

    public long getFileID() {
        return fileID;
    }

    public long getAccessLevel() {
        return accessLevel;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
