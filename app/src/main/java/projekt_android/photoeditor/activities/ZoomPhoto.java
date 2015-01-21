package projekt_android.photoeditor.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;

import projekt_android.photoeditor.R;


public class ZoomPhoto extends Activity {
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_photo);

        Intent intent = getIntent();
        path = intent.getStringExtra("img_path");

        ImageView imageView = (ImageView)findViewById(R.id.zoomImageView);
        File imageFile = new File(path);
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            //bitmap = Bitmap.createScaledBitmap(bitmap, cellWidth -8, cellWidth -8, true);
            imageView.setImageBitmap(bitmap);
        }
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
