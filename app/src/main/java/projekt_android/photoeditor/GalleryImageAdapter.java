package projekt_android.photoeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.List;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class GalleryImageAdapter extends BaseAdapter {

    private List<String> urls;
    private Context context;

    public GalleryImageAdapter(List<String> urls, Context context) {
        this.urls = urls;
        this.context = context;
    }

    @Override
    public int getCount() {
        if(urls == null) return 0;
        return urls.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            imageView = new ImageView(context);
            // maybe set some more imageView params for gridlayout
        } else {
            imageView = (ImageView) view;
        }
        String url = urls.get(i);
        File imageFile = new File(url);
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        } else {
            urls.remove(i);
        }
        return imageView;

    }
}

