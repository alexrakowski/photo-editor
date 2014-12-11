package projekt_android.photoeditor.database;

import android.content.Context;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class ContentDataSource extends ImageDataSource {

    public ContentDataSource(Context context) {
        super(context);
        allColumns = new String[] {ImageSQLHelper.COLUMN_CONTENT_ID, ImageSQLHelper.COLUMN_CONTENT_LINK};
    }

    @Override
    public String getImageColumnName() {
        return ImageSQLHelper.COLUMN_CONTENT_LINK;
    }

    @Override
    public String getTableName() {
        return ImageSQLHelper.TABLE_CONTENT;
    }
}
