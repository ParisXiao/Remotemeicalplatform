package com.gxey.remotemedicalplatform.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by xusongsong on 2016/11/22.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    // 数据库名称
    public static final String DATABASE_NAME = "pharmacy.db";
    // 数据库版本号
    public static final int DATABASE_VERSION = 1;
    // 定义一个SQLiteDatabase对象，对表进行相应的操作
    private SQLiteDatabase mDatabase;



    public MySQLiteOpenHelper(Context context){

        super(context, MySQLiteOpenHelper.DATABASE_NAME, null, MySQLiteOpenHelper.DATABASE_VERSION);
        mDatabase = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

           db.execSQL(Information.Information);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 添加数据
     * @param cv
     * @return
     */
    public boolean insertData(ContentValues cv){
        return mDatabase.insert(Information.TABLE_Information, null, cv)>0;
    }
    public void deleteAll() {
        mDatabase.delete(Information.TABLE_Information, null, new String[]{});
    }
//   /**
//     * 查询所有数据
//     * @return
//     */
//    public List<Clinicsentity> queryData(){
//
//        List<Clinicsentity> userinfos=new ArrayList<>();
//        //从数据库里查询数据
//        Cursor cursor=mDatabase.query(Clinics.TABLE_clinics, null, null, null, null, null, null,null);
//
//        if(cursor!=null){
//            //取出数据
//            while (cursor.moveToNext()) {
//                Clinicsentity clinics=new Clinicsentity();
//                clinics.setClinic_Id(cursor.getInt(0));
//                clinics.setClinicAccount(cursor.getString(1));
//                clinics.setPasswords(cursor.getString(2));
//                clinics.setType(cursor.getString(3));
//                clinics.setClinic_name(cursor.getString(4));
//                clinics.setTelephone(cursor.getString(5));
//                clinics.setAddress(cursor.getString(6));
//                clinics.setQuerstion1(cursor.getString(7));
//                clinics.setAnswer1(cursor.getString(8));
//                clinics.setQuerstion2(cursor.getString(9));
//                clinics.setAnswer2(cursor.getString(10));
//                clinics.setQuerstion3(cursor.getString(11));
//                clinics.setAnswer3(cursor.getString(12));
//                clinics.setCreateTime(cursor.getString(13));
//                clinics.setIsDel(cursor.getInt(14));
//                userinfos.add(clinics);
//            }
//
//        }
//        return userinfos;
//
//    }

}
