package projekt_android.photoeditor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class SelectContentToAdd extends Activity {
    private String selectedImagePath;
    private Bitmap editedImage;
    public final static String IMAGE_PATH = "projekt_android.photoeditor.IMAGE_PATH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_content_to_add);

        Intent intent = getIntent();
        //selectedImagePath = intent.getStringExtra(MainMenu.IMAGE_PATH);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.select_content_to_add, menu);
        return true;
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

    // SELECT CONTENT
    public void imageClick(View view) {
        ImageView image = (ImageView) view;
        boolean selected;
        Object tag = image.getTag();
        if(tag != null) {
             selected = (Boolean) tag;
        } else {
            selected = false;
        }
        Resources resources = getResources();
        if(!selected) {
            view.setBackgroundColor(resources.getColor(R.color.selected_content_blue));
        } else {
            view.setBackgroundColor(resources.getColor(R.color.background_blue));
        }
        view.setTag(new Boolean(!selected));
    }

    // GO TO NEXT ACTIVITY
    public void startImageConfirmal(View view){
        Intent intent = new Intent(this, PhotoEditionConfirmal.class);
        editedImage = BitmapFactory.decodeFile(selectedImagePath);
        intent.putExtra("editedImage", editedImage);
        startActivity(intent);
    }
}
