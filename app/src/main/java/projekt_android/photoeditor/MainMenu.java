package projekt_android.photoeditor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainMenu extends Activity {

    private static final int SELECT_IMAGE_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private String selectedImagePath;
    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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

    // METHODS FOR SELECTING A PICTURE
    public void showImageSelectionDialog(View view) {
        String [] imageSelectionOptions = {"Select from Gallery", "Use the Camera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Source for the image:")
                .setItems(imageSelectionOptions, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which)
                        {
                            case 0:
                                selectImageFromMemory();
                                break;
                            case 1:
                                selectImageFromCamera();
                                break;
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ImageView image = (ImageView) findViewById(R.id.selectImageImageView);
            switch (requestCode)
            {
                case SELECT_IMAGE_CODE:
                    Uri selectedImageUri = data.getData();
                    selectedImagePath = getPath(selectedImageUri);

                    selectedImage = BitmapFactory.decodeFile(selectedImagePath);
                    image.setImageBitmap(selectedImage);
                    break;
                case CAMERA_REQUEST_CODE:
                    selectedImage = (Bitmap) data.getExtras().get("data");
                    image.setImageBitmap(selectedImage);
                    break;
            }
        }
    }

    private void selectImageFromCamera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private void selectImageFromMemory(){
        Intent gallery = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECT_IMAGE_CODE);
    }

    // helper method
    private String getPath(Uri uri){
        String[] filePathColumn={MediaStore.Images.Media.DATA};

        Cursor cursor=getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex=cursor.getColumnIndex(filePathColumn[0]);

        return cursor.getString(columnIndex);
    }

    // GO TO NEXT ACTIVITY
    public void startImageEditing(View view){
        //set the photo to edit
        PhotoEditorApp appContext = ((PhotoEditorApp)getApplicationContext());
        appContext.savePreEditedBitmap(selectedImage);

        Intent intent = new Intent(this, SelectContentToAdd.class);
        startActivity(intent);
    }

}
