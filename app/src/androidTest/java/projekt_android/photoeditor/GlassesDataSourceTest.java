package projekt_android.photoeditor;

import android.content.Context;

import projekt_android.photoeditor.database.GlassesDataSource;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class GlassesDataSourceTest extends ImageDataSourceTest {

    @Override
    public void setNewDataSource(Context context) {
        setDataSource(new GlassesDataSource(context));
    }
}
