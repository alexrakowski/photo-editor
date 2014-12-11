package projekt_android.photoeditor.database;

import android.content.Context;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class MoustachesDataSource extends ImageDataSource{

    public MoustachesDataSource(Context context) {
        super(context);
        allColumns = new String[]{ImageSQLHelper.COLUMN_MOUSTACHES_ID, ImageSQLHelper.COLUMN_MOUSTACHES_LINK};
    }

    @Override
    public String getImageColumnName() {
        return ImageSQLHelper.COLUMN_MOUSTACHES_LINK;
    }

    @Override
    public String getTableName() {
        return ImageSQLHelper.TABLE_MOUSTACHES;
    }
}