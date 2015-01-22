package projekt_android.photoeditor.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2014-12-10.
 */
public abstract class ImageDataSource {

    protected ImageSQLHelper helper;
    protected SQLiteDatabase database;
    protected String[] allColumns;

    public ImageDataSource(Context context) {
        this.helper = new ImageSQLHelper(context);
    }

    public void open() {
        try {
            database = helper.getWritableDatabase();
        } catch(Exception exc) {
            Log.w(ImageDataSource.class.getName(), "Could not open database");
            database = null;
        }
    }

    public void close() {
        helper.close();
    }

    public abstract String getImageColumnName();

    public abstract String getTableName();

    public void addImage(String url) {
        ContentValues values = new ContentValues();
        values.put(getImageColumnName(), url);
        database.insert(getTableName(), null, values);
    }

    public List<String> getAllUrls() {
        List<String> urls = new ArrayList<String>();

        Cursor cursor = database.query(getTableName(), allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String url = cursor.getString(1);
            urls.add(url);
            cursor.moveToNext();
        }

        return urls;
    }
}
