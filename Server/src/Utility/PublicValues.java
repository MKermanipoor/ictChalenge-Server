package Utility;

import database.dataManager.DataManager;
import database.dataManager.DataManagerIMP;

import java.util.Scanner;

public abstract class PublicValues {
    public static final DataManager DATA_MaMANAGER = DataManagerIMP.GetDataManger();

    //server config
    public static final int SERVER_PORT = 8080;


    public static final Scanner SCANNER = new Scanner(System.in);
}
