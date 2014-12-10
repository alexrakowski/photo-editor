package projekt_android.photoeditor;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Alek on 2014-12-10.
 */
public class Utils {
    public static void showSimpleDialog(Context context, String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(text);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
