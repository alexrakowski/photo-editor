package projekt_android.photoeditor.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import projekt_android.photoeditor.Utils;
import projekt_android.photoeditor.activities.ZoomPhoto;

/**
 * Created by Piotrek on 2014-12-11.
 */
public class GalleryImageAdapter extends BaseAdapter {

    private List<String> urls;
    private Context context;
    private static int cellWidth;

    public List<String> getUrls() {
        return urls;
    }

    public GalleryImageAdapter(List<String> urls, Context context, int width) {
        filterAndSetUrls(urls);
        this.context = context;
        this.cellWidth = width;
    }

    public GalleryImageAdapter(List<String> urls, Context context) {
        filterAndSetUrls(urls);
        this.context = context;
    }

    private void filterAndSetUrls(List<String> nonFilteredUrls){
        this.urls = new ArrayList<String>();

        //filter out not found files
        for (int i=0; i<nonFilteredUrls.size(); i++){
            File imageFile = new File(nonFilteredUrls.get(i));
            if (imageFile.exists()) {
                this.urls.add(nonFilteredUrls.get(i));
            }
        }
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
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);
        } else {
            imageView = (ImageView) view;
        }
        String url = urls.get(i);
        File imageFile = new File(url);
        if (imageFile.exists()) {
            Bitmap bitmap = Utils.decodeSampledBitmapFromFile(imageFile.getAbsolutePath(), 100, 100 );//BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
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

