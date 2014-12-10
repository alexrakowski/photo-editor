package projekt_android.photoeditor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PhotoEditionConfirmal extends Activity {
    private Bitmap editedImage;

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
        }else
        {
            resultMessage = "Failed to save the photo";
        }

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), resultMessage, duration);
        toast.show();
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
    /////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_edition_confirmal);

        PhotoEditorApp photoEditorApp = ((PhotoEditorApp)getApplicationContext());
        editedImage = photoEditorApp.getEditedPhoto();

        ImageView imageView = (ImageView) findViewById(R.id.editedImageImageView);
        imageView.setImageBitmap(editedImage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo_edition_confirmal, menu);
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


    //not used

    private void addFileToGallery(String filename, String photoName){
        String description = getString(R.string.saved_photo_description);
        String savedImage, resultMessage;
        try {
            savedImage = MediaStore.Images.Media.insertImage(getContentResolver(), filename, photoName, description);
        }
        catch (Exception e)
        {
            resultMessage = "Failed to add the photo to the gallery";
            Utils.showSimpleDialog(this, resultMessage);
            e.printStackTrace();
            return;
        }
        resultMessage = "Successfully added the photo (" + savedImage +") to the gallery";

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), resultMessage, duration);
        toast.show();
    }

    private void addBitmapToGallery(){
        String description = getString(R.string.saved_photo_description);
        String savedImage = MediaStore.Images.Media.insertImage(getContentResolver(), editedImage, getTimeStampFileName(), description);
        String resultMessage;

        if (savedImage != null){
            resultMessage = "Successfully saved the photo (" + savedImage +")";

            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getApplicationContext(), resultMessage, duration);
            toast.show();
        }else{
            resultMessage = "Failed to save the photo";
            Utils.showSimpleDialog(this, resultMessage);
        }
    }
}
