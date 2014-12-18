package projekt_android.photoeditor;

import android.content.Context;

import projekt_android.photoeditor.database.MoustachesDataSource;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class MoustachesDataSourceTest extends ImageDataSourceTest {

    @Override
    public void setNewDataSource(Context context) {
        setDataSource(new MoustachesDataSource(context));
    }

}
