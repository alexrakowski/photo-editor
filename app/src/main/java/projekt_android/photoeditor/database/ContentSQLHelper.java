package projekt_android.photoeditor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Piotrek on 2014-12-10.
 */
public class ContentSQLHelper extends ImageSQLHelper {

    private static final String DATABASE_NAME = "content.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTENT = "content";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTENT_LINK = "contentlink";

    private static final String CREATE = String.format("create table %s (%s integer primary key autoincrement, %s text not null);");

    public ContentSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i(ContentSQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
        onCreate(sqLiteDatabase);
    }

    @Override
    public String getTableName() {
        return TABLE_CONTENT;
    }

    @Override
    public String getImageColumnName() {
        return COLUMN_CONTENT_LINK;
    }

    @Override
    public String getIdColumnName() {
        return COLUMN_ID;
    }
}
