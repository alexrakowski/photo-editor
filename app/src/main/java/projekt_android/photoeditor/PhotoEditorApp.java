package projekt_android.photoeditor;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Alek on 2014-12-10.
 */
public class PhotoEditorApp extends Application {
    private Bitmap preEditedPhoto;

    public Bitmap getPreEditedPhoto() {
        return preEditedPhoto;
    }
    public void setPreEditedPhoto(Bitmap preEditedPhoto) {
        this.preEditedPhoto = preEditedPhoto;
    }

    //Files Utils
    private String saveBitmapToInternalStorage(Bitmap bitmapImage, String filename) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, filename + ".jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    public void savePreEditedBitmap(Bitmap bitmap){
        setPreEditedPhoto(bitmap);
        saveBitmapToInternalStorage(preEditedPhoto, "photo_to_edit");
    }
}
