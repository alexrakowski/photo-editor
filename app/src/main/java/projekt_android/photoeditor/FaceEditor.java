package projekt_android.photoeditor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.FaceDetector;
import android.util.Log;

import java.util.Random;

public class FaceEditor {
    private final static int MAX_FACES = 3;

    public static Bitmap editBitmap(Bitmap bitmap, Bitmap [] moustaches, Bitmap [] hats, Bitmap [] glasses){
        FaceDetector.Face[] faces = getFacesFromImage(bitmap);
        if (faces == null)
        {
            //no faces found
            return bitmap;
        }

        Bitmap edited = addContentToImage(bitmap, faces, moustaches, hats, glasses);

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

    private static Bitmap addContentToImage(Bitmap faceBitmap, FaceDetector.Face [] faces, Bitmap [] moustaches, Bitmap [] hats, Bitmap [] glasses) {
        Bitmap faceWithContent = faceBitmap.copy(faceBitmap.getConfig(), true);
        Bitmap moustacheBitmap, glassesBitmap, hatBitmap;

        for (FaceDetector.Face face : faces) {
            if (face != null) {
                moustacheBitmap = moustaches[new Random().nextInt(moustaches.length)];
                faceWithContent = addMoustache(faceWithContent, moustacheBitmap, face);

                hatBitmap = hats[new Random().nextInt(hats.length)];
                faceWithContent = addHat(faceWithContent, hatBitmap, face);

                glassesBitmap = glasses[new Random().nextInt(glasses.length)];
                faceWithContent = addGlasses(faceWithContent, glassesBitmap, face);
            }
        }

        return  faceWithContent;
    }

    private static final float GLASSES_OFFSET_RATIO = 0.4f;
    private static final float GLASSES_WIDTH_RATIO = 2.1f;
    private static final float GLASSES_HEIGHT_RATIO = 1.0f;

    private static Bitmap addGlasses(Bitmap bitmap, Bitmap glasses, FaceDetector.Face face){
        PointF hatPosition = new PointF();

        //scale the glasses bitmap
        glasses = scaleGlasses(glasses, face.eyesDistance());

        //set the approximate position for the glasses
        face.getMidPoint(hatPosition);
        float dh = GLASSES_OFFSET_RATIO * face.eyesDistance();
        hatPosition.offset(0, -dh);

        //add the glasses to the image
        hatPosition.offset(-glasses.getWidth() / 2, 0);
        bitmap = overlayBitmaps(bitmap, glasses, hatPosition.x, hatPosition.y);

        return bitmap;
    }
    private static Bitmap scaleGlasses(Bitmap g, float eyesDistance){
        int width = Math.round(GLASSES_WIDTH_RATIO * eyesDistance);
        int height = Math.round(GLASSES_HEIGHT_RATIO * eyesDistance);

        Bitmap scaled = Bitmap.createScaledBitmap(g, width, height, false);

        return scaled;
    }

    private static final float HAT_OFFSET_RATIO = 2.8f;
    private static final float HAT_WIDTH_RATIO = 2.4f;
    private static final float HAT_HEIGHT_RATIO = 2.4f;

    private static Bitmap addHat(Bitmap bitmap, Bitmap hat, FaceDetector.Face face){
        PointF hatPosition = new PointF();

        //scale the hat bitmap
        hat = scaleHat(hat, face.eyesDistance());

        //set the approximate position for the hat
        face.getMidPoint(hatPosition);
        float dh = HAT_OFFSET_RATIO * face.eyesDistance();
        hatPosition.offset(0, -dh);

        //add the hat to the image
        hatPosition.offset(-hat.getWidth() / 2, 0);
        bitmap = overlayBitmaps(bitmap, hat, hatPosition.x, hatPosition.y);

        return bitmap;
    }
    private static Bitmap scaleHat(Bitmap h, float eyesDistance){
        int width = Math.round(HAT_WIDTH_RATIO * eyesDistance);
        int height = Math.round(HAT_HEIGHT_RATIO * eyesDistance);

        Bitmap scaled = Bitmap.createScaledBitmap(h, width, height, false);

        return scaled;
    }

    private static final float MOUSTACHE_OFFSET_RATIO = 0.67f;
    private static final float MOUSTACHE_WIDTH_RATIO = 1.62f;
    private static final float MOUSTACHE_HEIGHT_RATIO = 0.46f;

    private static Bitmap addMoustache(Bitmap bitmap, Bitmap moustache, FaceDetector.Face face){
        PointF moustachePosition = new PointF();

        //scale the moustache bitmap
        moustache = scaleMoustache(moustache, face.eyesDistance());

        //set the approximate position for the moustache
        face.getMidPoint(moustachePosition);
        float dh = MOUSTACHE_OFFSET_RATIO * face.eyesDistance();
        moustachePosition.offset(0, dh);

        //add the moustache to the image
        moustachePosition.offset(-moustache.getWidth() / 2, 0);
        bitmap = overlayBitmaps(bitmap, moustache, moustachePosition.x, moustachePosition.y);

        return bitmap;
    }
    private static Bitmap scaleMoustache(Bitmap m, float eyesDistance){
        int width = Math.round(MOUSTACHE_WIDTH_RATIO * eyesDistance);
        int height = Math.round(MOUSTACHE_HEIGHT_RATIO * eyesDistance);

        Bitmap scaled = Bitmap.createScaledBitmap(m, width, height, false);

        return scaled;
    }
}
