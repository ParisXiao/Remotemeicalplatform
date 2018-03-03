package com.gxey.remotemedicalplatform.db;

/**
 * Created by xusongsong on 2016/11/29.
 */
public class Information {

    public static final String TABLE_Information= "Information";
    //字段名
    public static final String Id = "Id";
    public static final String PositionID = "PositionID";
    public static final String UName = "UName";
    public static final String UNum = "UNum";
    public static final String Sex = "Sex";
    public static final String MobileNum = "MobileNum";
    public static final String Addr = "Addr";
    public static final String password = "password";

    public static final String Information =
            "create table if not exists "+TABLE_Information+" ("+
                    Id+ "  Nvarchar(50) ," +
                    PositionID+ " Nvarchar(50) ," +
                    UName+ " Nvarchar(50) ," +
                    UNum+ " Nvarchar(50)," +
                    Sex+ " Nvarchar(1)," +
                    MobileNum+ " iNvarchar(50)," +
                    password+ " iNvarchar(50)," +
                    Addr+ " Nvarchar(50)"
                    +")";
}
