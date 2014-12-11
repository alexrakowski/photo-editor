package projekt_android.photoeditor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Piotrek on 2014-12-10.
 */
public class ImageSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "content.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTENT = "content";
    public static final String COLUMN_CONTENT_ID = "_id";
    public static final String COLUMN_CONTENT_LINK = "contentlink";

    public static final String TABLE_GALLERY = "gallery";
    public static final String COLUMN_GALLERY_ID = "_id";
    public static final String COLUMN_GALLERY_LINK = "gallerylink";

    private static final String CREATE_CONTENT = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_CONTENT, COLUMN_CONTENT_ID, COLUMN_CONTENT_LINK);
    private static final String CREATE_GALLERY = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_GALLERY, COLUMN_GALLERY_ID, COLUMN_GALLERY_LINK);

    public ImageSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CONTENT);
        sqLiteDatabase.execSQL(CREATE_GALLERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(ImageSQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);
        onCreate(sqLiteDatabase);
    }

}
