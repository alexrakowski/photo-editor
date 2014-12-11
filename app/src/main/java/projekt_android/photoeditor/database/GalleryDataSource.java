package projekt_android.photoeditor.database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class GalleryDataSource extends ImageDataSource {

    public GalleryDataSource(Context context) {
        super(context);
        allColumns = new String[] {ImageSQLHelper.COLUMN_GALLERY_ID, ImageSQLHelper.COLUMN_GALLERY_LINK};
    }

    @Override
    public String getImageColumnName() {
        return ImageSQLHelper.COLUMN_GALLERY_LINK;
    }

    @Override
    public String getTableName() {
        return ImageSQLHelper.TABLE_GALLERY;
    }

}
