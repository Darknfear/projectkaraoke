package topica.edu.vn.karaoke;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Music.db";
    public static final String TABLE_NAME = "music";
    public static final String COLUMNS_ID = "ID";
    public static final String COLUMNS_MASO = "MaSo";
    public static final String COLUMNS_TENBAIHAT = "TenBaiHat";
    public static final String COLUMNS_TENTACGIA = "TenTacGia";
    public static final String COLUMNS_THICH = "Thich";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS music(ID INTEGER PRIMARY KEY AUTOINCREMENT,TenBT VARCHAR NOT NULL,TenTG VARCHAR NOT NULL,YeuThich INTEGER DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS music");
        onCreate(db );

    }
    public boolean insertMusic(String tenBaiHat,String tenTacGia){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TenBT",tenBaiHat);
        contentValues.put("TenTG",tenTacGia);
        database.insert("music",null,contentValues);
        return true;
    }
    public Cursor getData(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("music",null,"YeuThich = '"+i+"'",null,null,null,null);
        return cursor;
    }
    public boolean upDate(int id,int yeuThich){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("YeuThich",yeuThich);
        db.update("music",contentValues,"ID = ?",new String[]{Integer.toString(id)});
        return true;
    }
}
