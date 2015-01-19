package projekt_android.photoeditor.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projekt_android.photoeditor.PhotoEditorApp;
import projekt_android.photoeditor.R;
import projekt_android.photoeditor.Utils;
import projekt_android.photoeditor.database.GalleryDataSource;


public class PhotoEditionConfirmal extends Activity {

    private Bitmap editedImage;

    private GalleryDataSource dataSource;

    private UiLifecycleHelper uiHelper;

    /**
     * adds the edited photo to Gallery, and saves to internal storage
     */
    public void saveEditedPhoto(View view){
        PhotoEditorApp photoEditorApp = (PhotoEditorApp)getApplicationContext();
        String photoName, filename, resultMessage;

        photoName = getTimeStampFileName();
        filename = photoEditorApp.saveBitmapFile(editedImage, photoName);
        if (filename != null)
        {
            scanMedia(filename);
            resultMessage = "Successfully saved the photo (" + filename +")";
            dataSource.addImage(filename);
        }else
        {
            resultMessage = "Failed to save the photo";
        }

        Utils.showShortToast(getApplicationContext(), resultMessage);
    }

    public void returnToMainMenu(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    private String getTimeStampFileName(){
        String fileName = "PH_";
        fileName += new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        fileName += ".png";

        return fileName;
    }

    private void scanMedia(String path) {
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Intent scanFileIntent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
        sendBroadcast(scanFileIntent);
    }

    public void shareOnFacebook(View view) {
        if (FacebookDialog.canPresentShareDialog(getApplicationContext(), FacebookDialog.ShareDialogFeature.PHOTOS)) {
            List<Bitmap> photo = new ArrayList<Bitmap>();
            photo.add(editedImage);
            FacebookDialog shareDialog = new FacebookDialog
                    .PhotoShareDialogBuilder(this)
                    .setApplicationName(getString(R.string.app_name))
                    .addPhotos(photo)
                    .build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else {
            Utils.showShortToast(getApplicationContext(), "Can't display 'share on Facebook' dialog");
        }
    }

    /////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_edition_confirmal);

        this.uiHelper = new UiLifecycleHelper(this, null);
        uiHelper.onCreate(savedInstanceState);

        PhotoEditorApp photoEditorApp = ((PhotoEditorApp)getApplicationContext());
        editedImage = photoEditorApp.getEditedPhoto();

        ImageView imageView = (ImageView) findViewById(R.id.editedImageImageView);
        imageView.setImageBitmap(editedImage);

        dataSource = new GalleryDataSource(getApplication());
        dataSource.open();
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        dataSource.close();
        uiHelper.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiHelper.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
            @Override
            public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
                Log.e(PhotoEditionConfirmal.class.getName(), String.format("Error: %s", error.toString()));
            }

            @Override
            public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
                Log.i(PhotoEditionConfirmal.class.getName(), "Facebook share success");
            }
        });
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
