package projekt_android.photoeditor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Piotrek on 2014-12-10.
 */
public abstract class ImageSQLHelper extends SQLiteOpenHelper {

    public abstract String getTableName();

    public abstract String getImageColumnName();

    public abstract String getIdColumnName();

    public ImageSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
}
