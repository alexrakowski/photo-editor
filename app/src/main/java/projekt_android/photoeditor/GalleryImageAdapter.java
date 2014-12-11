package projekt_android.photoeditor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class GalleryImageAdapter extends BaseAdapter {

    private List<String> urls;
    private Context context;
    private static int cellWidth = 130;

    public GalleryImageAdapter(List<String> urls, Context context) {
        this.urls = new ArrayList<String>();
        for (int i=0; i<urls.size(); i++){
            File imageFile = new File(urls.get(i));
            if (imageFile.exists()) {
                this.urls.add(urls.get(i));
            }
        }
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

            imageView.setLayoutParams(new GridView.LayoutParams(cellWidth -8, cellWidth -8));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) view;
        }
        String url = urls.get(i);
        File imageFile = new File(url);
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            //bitmap = Bitmap.createScaledBitmap(bitmap, cellWidth -8, cellWidth -8, true);
            imageView.setImageBitmap(bitmap);
        } else {
            //urls.remove(i);
        }
        imageView.setOnClickListener(new OnImageClickListener(url));
        return imageView;
    }

    class OnImageClickListener implements View.OnClickListener {

        private String img_path;

        // constructor
        public OnImageClickListener(String position) {
            this.img_path = position;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(context, ZoomPhoto.class);
            i.putExtra("img_path", img_path);
            context.startActivity(i);
        }

    }
}

