package projekt_android.photoeditor;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import projekt_android.photoeditor.content.PhotoContent;
import projekt_android.photoeditor.database.GlassesDataSource;
import projekt_android.photoeditor.database.HatsDataSource;
import projekt_android.photoeditor.database.MoustachesDataSource;


public class SelectContentToAdd extends Activity {

    private static final int SELECT_GLASSES_CODE = 1;
    private static final int SELECT_MOUSTACHES_CODE = 2;
    private static final int SELECT_HATS_CODE = 3;

    private GlassesDataSource glassesSource;
    private MoustachesDataSource moustachesSource;
    private HatsDataSource hatsSource;

    // SELECT CONTENT
    public void imageClick(View view) {
        ImageView image = (ImageView) view;
        boolean selected;
        Object tag = image.getTag();
        if(tag != null) {
            selected = (Boolean) tag;
        } else {
            selected = false;
        }
        Resources resources = getResources();
        if(!selected) {
            view.setBackgroundColor(resources.getColor(R.color.selected_content_blue));
        } else {
            view.setBackgroundColor(resources.getColor(R.color.background_blue));
        }
        view.setTag(new Boolean(!selected));
    }

    public void editPhoto(){
        PhotoEditorApp photoEditorApp = (PhotoEditorApp)getApplicationContext();
        Bitmap photoToEdit = photoEditorApp.getPreEditedPhoto();
        Bitmap [] moustaches = getSelectedMoustaches();
        Bitmap [] hats = getSelectedHats();
        Bitmap [] glasses = getSelectedGlasses();

        ArrayList<PhotoContent> addedContent = FaceEditor.editBitmap(photoToEdit, moustaches, hats, glasses);

        photoEditorApp.setPhotoContents(addedContent);
    }

    private Bitmap[] getSelectedMoustaches(){
        LinearLayout moustachesRow = (LinearLayout) findViewById(R.id.moustachesLayout);

        int selectedCount = 0;
        for(int i=0; i<moustachesRow.getChildCount(); i++) {
            ImageView child = (ImageView)moustachesRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                selectedCount++;
            }
        }

        Bitmap [] moustaches = new Bitmap[selectedCount];
        if (selectedCount == 0)
            return null;

        selectedCount=0;

        for(int i=0; i<moustachesRow.getChildCount(); i++) {
            ImageView child = (ImageView)moustachesRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                moustaches[selectedCount] = ((BitmapDrawable)child.getDrawable()).getBitmap();
                selectedCount++;
            }
        }
        return moustaches;
    }
    private Bitmap[] getSelectedGlasses(){
        LinearLayout glassesRow = (LinearLayout) findViewById(R.id.glassesLayout);

        int selectedCount = 0;
        for(int i=0; i<glassesRow.getChildCount(); i++) {
            ImageView child = (ImageView)glassesRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                selectedCount++;
            }
        }

        Bitmap [] glasses = new Bitmap[selectedCount];
        if (selectedCount == 0)
            return null;
        selectedCount=0;

        for(int i=0; i<glassesRow.getChildCount(); i++) {
            ImageView child = (ImageView)glassesRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                glasses[selectedCount] = ((BitmapDrawable)child.getDrawable()).getBitmap();
                selectedCount++;
            }
        }
        return glasses;
    }
    private Bitmap[] getSelectedHats(){
        LinearLayout hatsRow = (LinearLayout) findViewById(R.id.hatsLayout);

        int selectedCount = 0;
        for(int i=0; i<hatsRow.getChildCount(); i++) {
            ImageView child = (ImageView)hatsRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                selectedCount++;
            }
        }
        if(selectedCount == 0)
            return null;

        Bitmap [] hats = new Bitmap[selectedCount];
        selectedCount=0;

        for(int i=0; i<hatsRow.getChildCount(); i++) {
            ImageView child = (ImageView)hatsRow.getChildAt(i);
            Object tag = child.getTag();
            if (tag != null && (Boolean)tag){
                hats[selectedCount] = ((BitmapDrawable)child.getDrawable()).getBitmap();
                selectedCount++;
            }
        }
        return hats;
    }

    // ADD METHODS
    public void addGlasses(View view) {
        Log.i(SelectContentToAdd.class.getName(), "Adding glasses");
        selectImageFromMemory(SELECT_GLASSES_CODE);
    }

    public void addMoustaches (View view) {
        Log.i(SelectContentToAdd.class.getName(), "Adding moustaches");
        selectImageFromMemory(SELECT_MOUSTACHES_CODE);
    }

    public void addHats(View view) {
        Log.i(SelectContentToAdd.class.getName(), "Adding hats");
        selectImageFromMemory(SELECT_HATS_CODE);
    }

    public void addImgToLayout (String url, LinearLayout layout) {
        ImageView view = new ImageView(this);
        File imageFile = new File(url);
        if (imageFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            view.setImageBitmap(bitmap);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 45);
            params.setMargins(8,8,8,8);
            view.setPadding(4,4,4,4);
            view.setLayoutParams(params);
            view.setAdjustViewBounds(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageClick(view);
                }
            });

            layout.addView(view);
        }
    }

    private void selectImageFromMemory(int code) {
        Intent gallery = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, code);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case(SELECT_GLASSES_CODE) : {
                    Uri selectedImageUri = data.getData();
                    String selectedImagePath = Utils.getPath(selectedImageUri, getContentResolver());
                    glassesSource.addImage(selectedImagePath);
                    addImgToLayout(selectedImagePath, (LinearLayout) findViewById(R.id.glassesLayout));
                    break;
                }
                case(SELECT_MOUSTACHES_CODE) : {
                    Uri selectedImageUri = data.getData();
                    String selectedImagePath = Utils.getPath(selectedImageUri, getContentResolver());
                    moustachesSource.addImage(selectedImagePath);
                    addImgToLayout(selectedImagePath, (LinearLayout) findViewById(R.id.moustachesLayout));
                    break;
                }
                case(SELECT_HATS_CODE) : {
                    Uri selectedImageUri = data.getData();
                    String selectedImagePath = Utils.getPath(selectedImageUri, getContentResolver());
                    hatsSource.addImage(selectedImagePath);
                    addImgToLayout(selectedImagePath, (LinearLayout) findViewById(R.id.hatsLayout));
                    break;
                }
            }

        }
    }

    // GO TO NEXT ACTIVITY
    public void startImageConfirmal(View view){
        editPhoto();
        //Intent intent = new Intent(this, PhotoEditionConfirmal.class);
        Intent intent = new Intent(this, MoveContent.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_content_to_add);

        glassesSource = new GlassesDataSource(getApplication());
        glassesSource.open();
        moustachesSource = new MoustachesDataSource(getApplication());
        moustachesSource.open();
        hatsSource = new HatsDataSource(getApplication());
        hatsSource.open();

        // add images from database
        List<String> glassesUrls = glassesSource.getAllUrls();
        List<String> moustachesUrls = moustachesSource.getAllUrls();
        List<String> hatsUrls = hatsSource.getAllUrls();

        for(String url : glassesUrls) {
            addImgToLayout(url, (LinearLayout) findViewById(R.id.glassesLayout));
        }
        for(String url : hatsUrls) {
            addImgToLayout(url, (LinearLayout) findViewById(R.id.hatsLayout));
        }
        for(String url : moustachesUrls) {
            addImgToLayout(url, (LinearLayout) findViewById(R.id.moustachesLayout));
        }
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        glassesSource.close();
        moustachesSource.close();
        hatsSource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.select_content_to_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
