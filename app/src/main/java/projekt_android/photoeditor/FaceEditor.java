package projekt_android.photoeditor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.FaceDetector;

public class FaceEditor {
    private final static int MAX_FACES = 3;

    public static Bitmap editBitmap(Bitmap bitmap, Bitmap moustache){
        //TODO: change arguments

        FaceDetector.Face[] faces = getFacesFromImage(bitmap);
        if (faces == null)
        {
            //no faces found
            return bitmap;
        }

        Bitmap edited = addContentToImage(bitmap, faces, moustache);

        return edited;
    }

    private static FaceDetector.Face [] getFacesFromImage(Bitmap bmp) {
        FaceDetector fd = new FaceDetector(bmp.getWidth(), bmp.getHeight(), MAX_FACES);
        FaceDetector.Face[] faces = new FaceDetector.Face[MAX_FACES];

        bmp = bmp.copy(Bitmap.Config.RGB_565, false);
        int faces_found_count = fd.findFaces(bmp, faces);

        //TODO: change the confidence
        if (faces_found_count > 0 && faces[0].confidence() > 0.25) {
            return faces;
        } else {
            return null;
        }
    }

    private static Bitmap overlayBitmaps(Bitmap bmp1, Bitmap bmp2, float dx, float dh) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, dx, dh, null);
        return bmOverlay;
    }

    private static Bitmap addContentToImage(Bitmap faceBitmap, FaceDetector.Face [] faces, Bitmap moustacheBitmap) {
        PointF moustachePosition = new PointF();
        Bitmap faceWithMoustache = faceBitmap;

        for (FaceDetector.Face face : faces) {
            if (face != null) {
                //set the approximate position for the moustache
                face.getMidPoint(moustachePosition);
                float dh = 0.67f * face.eyesDistance();
                moustachePosition.offset(0, dh);

                //add the moustache to the image
                moustachePosition.offset(-moustacheBitmap.getWidth() / 2, 0);
                //TODO: change, to overlay with already edited face (faceWithMoustache)
                faceWithMoustache = overlayBitmaps(faceBitmap, moustacheBitmap, moustachePosition.x, moustachePosition.y);
            }
        }

        return  faceWithMoustache;
    }
}
