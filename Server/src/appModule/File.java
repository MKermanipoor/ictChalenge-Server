package appModule;

import java.util.Date;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public class File {
   private String realLocation;
   private String userthingLocation;
   private long  userIdCreate;
   private long downloadCount;
   private Byte isDeleted;
   private long DirId;
   private String hash;
   private long size;
   private String mineType;
   private Date createTime;
   private long dbID;

    public File( long dbID,String realLocation, String userthingLocation, long userIdCreate, long downloadCount, Byte isDeleted, long dirId, String hash, long size, String mineType, Date createTime) {
        setRealLocation(realLocation);
        setUserthingLocation(userthingLocation);
        setUserIdCreate(userIdCreate);
        setDownloadCount(downloadCount);
        setDeleted(isDeleted);
        setDirId(dirId);
        setHash(hash);
        setSize(size);
        setMineType(mineType);
        setCreateTime(createTime);
        setDbID(dbID);
    }

    public void setRealLocation(String realLocation) {
        this.realLocation = realLocation;
    }

    public void setUserthingLocation(String userthingLocation) {
        this.userthingLocation = userthingLocation;
    }

    public void setUserIdCreate(long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public void setDownloadCount(long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setDeleted(Byte deleted) {
        isDeleted = deleted;
    }

    public void setDirId(long dirId) {
        DirId = dirId;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setMineType(String mineType) {
        this.mineType = mineType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setDbID(long dbID) {
        this.dbID = dbID;
    }

    public String getRealLocation() {
        return realLocation;
    }

    public String getUserthingLocation() {
        return userthingLocation;
    }

    public long getUserIdCreate() {
        return userIdCreate;
    }

    public long getDownloadCount() {
        return downloadCount;
    }

    public Byte isDeleted() {
        return isDeleted;
    }

    public long getDirId() {
        return DirId;
    }

    public String getHash() {
        return hash;
    }

    public long getSize() {
        return size;
    }

    public String getMineType() {
        return mineType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public long getDbID() {
        return dbID;
    }
}
