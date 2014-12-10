package projekt_android.photoeditor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import projekt_android.photoeditor.Gallery;

/**
 * Created by Piotrek on 2014-12-10.
 */
public class GallerySQLHelper extends ImageSQLHelper {

    private static final String DATABASE_NAME = "gallery.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_GALLERY = "gallery";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GALLERY_LINK = "gallerylink";

    private static final String CREATE = String.format("create table %s (%s integer primary key autoincrement, %s text not null);", TABLE_GALLERY, COLUMN_ID, COLUMN_GALLERY_LINK);

    public GallerySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(ContentSQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY
        );
        onCreate(sqLiteDatabase);
    }

    @Override
    public String getTableName() {
        return TABLE_GALLERY;
    }

    @Override
    public String getImageColumnName() {
        return COLUMN_GALLERY_LINK;
    }

    @Override
    public String getIdColumnName() {
        return COLUMN_ID;
    }
}
