package projekt_android.photoeditor.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.List;

import projekt_android.photoeditor.adapters.GalleryImageAdapter;
import projekt_android.photoeditor.R;
import projekt_android.photoeditor.database.GalleryDataSource;


public class Gallery extends Activity {
    private static final int GRID_VIEW_NUM_COLUMNS = 3;
    private GalleryDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        dataSource = new GalleryDataSource(getApplication());
        dataSource.open();

        Log.i(Gallery.class.getName(), "Trying to get all saved urls from database");
        List<String> urls = dataSource.getAllUrls();
        Log.i(Gallery.class.getName(), "Urls obtained");
        GridView grid = (GridView)findViewById(R.id.galleryGridView);

        grid.setNumColumns(GRID_VIEW_NUM_COLUMNS);
        grid.setAdapter(new GalleryImageAdapter(urls, this));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dataSource.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
