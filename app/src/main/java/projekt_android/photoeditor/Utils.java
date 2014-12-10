package projekt_android.photoeditor;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

public class Utils {
    public static void showSimpleDialog(Context context, String text){
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
}
