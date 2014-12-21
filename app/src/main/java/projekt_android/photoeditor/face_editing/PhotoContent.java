package projekt_android.photoeditor.face_editing;

import android.graphics.Bitmap;
import android.graphics.PointF;

/**
 * Created by Alek on 2014-12-15.
 */


public class PhotoContent {
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;
    }

    private Bitmap bitmap;
    private PointF position;

    public PhotoContent(Bitmap bitmap, PointF position){
        this.bitmap = bitmap;
        this.position = position;
    }

}
