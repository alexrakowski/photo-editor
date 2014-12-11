package projekt_android.photoeditor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Piotrek on 2014-12-10.
 */
public class ImageSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "images.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_GLASSES = "glasses";
    public static final String COLUMN_GLASSES_ID = "_id";
    public static final String COLUMN_GLASSES_LINK = "glasseslink";

    public static final String TABLE_MOUSTACHES = "moustaches";
    public static final String COLUMN_MOUSTACHES_ID = "_id";
    public static final String COLUMN_MOUSTACHES_LINK = "moustacheslink";

    public static final String TABLE_HATS = "hats";
    public static final String COLUMN_HATS_ID = "_id";
    public static final String COLUMN_HATS_LINK = "hatslink";

    public static final String TABLE_GALLERY = "gallery";
    public static final String COLUMN_GALLERY_ID = "_id";
    public static final String COLUMN_GALLERY_LINK = "gallerylink";

    private static final String CREATE_GLASSES = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_GLASSES, COLUMN_GLASSES_ID, COLUMN_GLASSES_LINK);
    private static final String CREATE_MOUSTACHES = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_MOUSTACHES, COLUMN_MOUSTACHES_ID, COLUMN_MOUSTACHES_LINK);
    private static final String CREATE_HATS = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_HATS, COLUMN_HATS_ID, COLUMN_HATS_LINK);

    private static final String CREATE_GALLERY = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_GALLERY, COLUMN_GALLERY_ID, COLUMN_GALLERY_LINK);

    public ImageSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_GLASSES);
        sqLiteDatabase.execSQL(CREATE_MOUSTACHES);
        sqLiteDatabase.execSQL(CREATE_HATS);
        sqLiteDatabase.execSQL(CREATE_GALLERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(ImageSQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GLASSES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUSTACHES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HATS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        onCreate(sqLiteDatabase);
    }

}
