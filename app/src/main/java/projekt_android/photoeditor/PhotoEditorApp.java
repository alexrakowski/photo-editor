package projekt_android.photoeditor;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import projekt_android.photoeditor.face_editing.PhotoContent;

/**
 * Created by Alek on 2014-12-10.
 */
public class PhotoEditorApp extends Application {
    private final static String albumName = "PhotoEditor";

    private Bitmap preEditedPhoto;
    private Bitmap editedPhoto;
    private ArrayList<PhotoContent> photoContents;

    public ArrayList<PhotoContent> getPhotoContents() {
        return photoContents != null ? photoContents : new ArrayList<PhotoContent>();
    }

    public void setPhotoContents(ArrayList<PhotoContent> photoContents) {
        if (this.photoContents != null){
            this.photoContents.clear();
        }
        this.photoContents = photoContents;
    }

    public Bitmap getEditedPhoto() {
        return editedPhoto;
    }
    public void setEditedPhoto(Bitmap editedPhoto) {
        Utils.recycleBitmap(this.editedPhoto);
        this.editedPhoto = editedPhoto;
    }

    public Bitmap getPreEditedPhoto() {
        return preEditedPhoto;
    }
    public void setPreEditedPhoto(Bitmap preEditedPhoto) {
        Utils.recycleBitmap(this.preEditedPhoto);
        this.preEditedPhoto = preEditedPhoto;
    }


    //Files Utils
    private String saveBitmapToInternalStorage(Bitmap bitmapImage, String filename) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, filename + ".jpg");

        FileOutputStream fos;
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

    public String saveBitmapFile(Bitmap bitmap, String filename){
        String path;
        if(isExternalStorageWritable()){
            File album = getAlbumStorageDir(getApplicationContext(), albumName);
            path = saveBitmapToExternalStorage(bitmap, filename, album);
        }else{
            Log.d("DEBUG", "external storage not writeable");
            return null;
        }
        return path;
    }

    private File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.isDirectory()) {
            //first check if directory is not already created
            if (!file.mkdirs()) {
                Log.e("<DEBUG>", "Directory not created");
            }
        }
        return file;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private String saveBitmapToExternalStorage(Bitmap bitmap, String filename, File storagePath){
        String result = null;
        FileOutputStream out = null;
        File bitmapFile = new File(storagePath, filename);

        try {
            out = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            // PNG is a lossless format, the compression factor (100) is ignored
            result = bitmapFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

