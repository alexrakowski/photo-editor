package projekt_android.photoeditor.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import projekt_android.photoeditor.views.MovableImageView;
import projekt_android.photoeditor.views.ParentImageView;
import projekt_android.photoeditor.PhotoEditorApp;
import projekt_android.photoeditor.R;
import projekt_android.photoeditor.Utils;
import projekt_android.photoeditor.face_editing.PhotoContent;


public class MoveContent extends Activity {
    private ParentImageView mainImageView;
    private ArrayList<MovableImageView> contentImageViews;


    //GO TO NEXT ACTIVITY
    public void finishEditing(View view){
        setEditedBitmap();
        Intent intent = new Intent(this, PhotoEditionConfirmal.class);
        startActivity(intent);
    }

    private void setEditedBitmap(){
        PhotoEditorApp photoEditorApp = ((PhotoEditorApp)getApplicationContext());
        Bitmap preEditedPhoto = photoEditorApp.getPreEditedPhoto();
        Bitmap editedPhoto = preEditedPhoto.copy(preEditedPhoto.getConfig(), true);

        for (MovableImageView contentImageView : contentImageViews){
            float dx, dy;
            Bitmap contentBitmap = contentImageView.getContentBitmap();//((BitmapDrawable) moustacheImageView.getDrawable()).getBitmap();

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)contentImageView.getLayoutParams();
            dx = layoutParams.leftMargin * ((float)preEditedPhoto.getWidth() / mainImageView.getWidth());
            dy = layoutParams.topMargin  * ((float)preEditedPhoto.getHeight() / mainImageView.getHeight());

            editedPhoto = Utils.overlayBitmaps(editedPhoto, contentBitmap, (int) dx, (int) dy);
        }

        photoEditorApp.setEditedPhoto(editedPhoto);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_content);

        PhotoEditorApp photoEditorApp = ((PhotoEditorApp)getApplicationContext());
        Bitmap editingImage = photoEditorApp.getPreEditedPhoto();

        mainImageView = (ParentImageView) findViewById(R.id.moveContentImageView);
        mainImageView.setImageBitmap(editingImage);

        loadContentImages();
    }

    private void loadContentImages(){
        contentImageViews = new ArrayList<MovableImageView>();
        PhotoEditorApp photoEditorApp = ((PhotoEditorApp)getApplicationContext());
        ArrayList<PhotoContent> contents = photoEditorApp.getPhotoContents();

        for (PhotoContent content : contents){
            MovableImageView movableImageView = (MovableImageView) getLayoutInflater().inflate(R.layout.movable_image_view, null);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.moveContentRelativeLayout);
            relativeLayout.addView(movableImageView, 1, layoutParams);

            movableImageView.setImageFromContent(content, mainImageView);

            mainImageView.addChild(movableImageView);
            contentImageViews.add(movableImageView);
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
