package projekt_android.photoeditor.database;

import android.content.Context;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class HatsDataSource extends ImageDataSource{

    public HatsDataSource(Context context) {
        super(context);
        allColumns = new String[]{ImageSQLHelper.COLUMN_HATS_ID, ImageSQLHelper.COLUMN_HATS_LINK};
    }

    @Override
    public String getImageColumnName() {
        return ImageSQLHelper.COLUMN_HATS_LINK;
    }

    @Override
    public String getTableName() {
        return ImageSQLHelper.TABLE_HATS;
    }
}