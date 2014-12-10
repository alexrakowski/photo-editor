package projekt_android.photoeditor.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2014-12-10.
 */
public class ImageDataSource {

    private ImageSQLHelper helper;
    private SQLiteDatabase database;
    private String[] allColumns;

    public ImageDataSource(ImageSQLHelper helper) {
        this.helper = helper;
        this.allColumns = new String[] {helper.getIdColumnName(), helper.getImageColumnName()};
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public void addImage(String url) {
        ContentValues values = new ContentValues();
        values.put(helper.getImageColumnName(), url);
        database.insert(helper.getTableName(), null, values);
    }

    public List<String> getAllUrls() {
        List<String> urls = new ArrayList<String>();

        Cursor cursor = database.query(helper.getTableName(), allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String url = cursor.getString(1);
            urls.add(url);
        }

        return urls;
    }
}
