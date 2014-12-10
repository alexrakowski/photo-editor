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

    public void editPhoto(){
        PhotoEditorApp photoEditorApp = (PhotoEditorApp)getApplicationContext();
        // TODO: CHANGE THIS!!!
        photoEditorApp.setEditedPhoto(photoEditorApp.getPreEditedPhoto());
    }

    // GO TO NEXT ACTIVITY
    public void startImageConfirmal(View view){
        editPhoto();
        Intent intent = new Intent(this, PhotoEditionConfirmal.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_content_to_add);
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
}
