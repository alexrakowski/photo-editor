package projekt_android.photoeditor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.android.Util;

import java.io.InputStream;

public class Utils {
    public static void showShortToast(Context context, String text){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public static void showSimpleDialog(Context context, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(text);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static Bitmap rotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Bitmap fixRotation(String photoPath, Bitmap bitmap) {
        if (photoPath.contains(".jpg")) {
            ExifInterface ei;
            try {
                ei = new ExifInterface(photoPath);

                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public static void recycleBitmap(Bitmap bitmap){
        if (bitmap != null && Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD){
            // we should check if the bitmap can be manually recycled
            bitmap.recycle();
        }
    }

    public static int [] getScreenDimensions(Context context){
        int [] dimens = new int [2];
        int width, height, dpi;

        DisplayMetrics displaymetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 13){
            Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        }else{
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            height = displaymetrics.heightPixels;
            width = displaymetrics.widthPixels;
        }
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        dpi = displaymetrics.densityDpi;
        width = width / (dpi );
        height = height / (dpi );

        dimens[0] = width;
        dimens[1] = height;
        return dimens;
    }

    public static Bitmap makeWidthEven(Bitmap bitmap){
        if (bitmap.getWidth() % 2 != 0){
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() - 1, bitmap.getHeight(), true);
        }

        return bitmap;
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap decodeSampledBitmapFromStream(InputStream inputStream, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);

        // Calculate inSampleSize
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int id, int reqWidth, int reqHeight, boolean withRgb565) {
        final BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        if (withRgb565)
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeResource(res, id, options);
        options.inSampleSize = Utils.calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, id, options);
    }

    public static boolean gingerbreadOrLower(){
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD;
    }

    public static String getPath(Uri uri, ContentResolver resolver) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = resolver.query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);
        cursor.close();

        return path;
    }

    public static Bitmap overlayBitmaps(Bitmap bmp1, Bitmap bmp2, float dx, float dh) {
        Bitmap bmOverlay = bmp1;//Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, dx, dh, null);
        return bmOverlay;
    }
}
