package appModule;

import java.util.Date;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class Dir {
    private String dirLocation;
    private long parentId;
    private Date createTime;
    private long dbID;

    public Dir(long dbID,String dirLocation, long parentId, Date createTime) {
       setDirLocation(dirLocation);
        setParentId(parentId);
        setCreateTime(createTime);
        setDbID(dbID);
    }

    public void setDirLocation(String dirLocation) {
        this.dirLocation = dirLocation;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    public String getDirLocation() {
        return dirLocation;
    }

    public long getParentId() {
        return parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public long getDbID() {
        return dbID;
    }
}
