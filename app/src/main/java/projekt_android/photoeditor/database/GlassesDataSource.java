package projekt_android.photoeditor.database;

import android.content.Context;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class GlassesDataSource extends ImageDataSource{

    public GlassesDataSource(Context context) {
        super(context);
        allColumns = new String[]{ImageSQLHelper.COLUMN_GLASSES_ID, ImageSQLHelper.COLUMN_GLASSES_LINK};
    }

    @Override
    public String getImageColumnName() {
        return ImageSQLHelper.COLUMN_GLASSES_LINK;
    }

    @Override
    public String getTableName() {
        return ImageSQLHelper.TABLE_GLASSES;
    }
}
