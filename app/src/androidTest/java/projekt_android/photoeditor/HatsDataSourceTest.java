package projekt_android.photoeditor;

import android.content.Context;

import projekt_android.photoeditor.database.HatsDataSource;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class HatsDataSourceTest extends ImageDataSourceTest {

    @Override
    public void setNewDataSource(Context context) {
        setDataSource(new HatsDataSource(context));
    }
}
