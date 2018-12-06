package database.dataManager;

import database.dao.DirDao;
import database.dao.FileDao;
import database.dao.UserDao;
import database.dao.UserFileDao;

/**
 * Created by saeidbahmani on 12/6/18.
 */
public interface DataManager {
    DirDao getDirDao();
    FileDao getFileDao();
    UserDao getUserDao();
    UserFileDao getUserFileDao();
}
