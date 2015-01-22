package projekt_android.photoeditor.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import projekt_android.photoeditor.face_editing.PhotoContent;

/**
 * Created by Alek on 2014-12-15.
 */
public class MovableImageView extends ImageView {
    private PhotoContent photoContent;
    private ImageView parentImageView;
    private float x, y;

    public void resetPosition(){
        if (this.photoContent != null)
            this.setPosition(this.photoContent.getPosition());
    }

    public void setPosition(float x, float y){
        RelativeLayout.LayoutParams myLayoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();
        RelativeLayout.LayoutParams parentLayoutParams = (RelativeLayout.LayoutParams) this.parentImageView.getLayoutParams();

        Bitmap parentImage = ((BitmapDrawable) parentImageView.getDrawable()).getBitmap();

        float parentXscale = (float)parentImageView.getWidth() / parentImage.getWidth();
        float parentYscale = (float)parentImageView.getHeight() / parentImage.getHeight();

        x = x * parentXscale;
        y = y * parentYscale;
        Bitmap bmp = photoContent.getBitmap();
        Bitmap scaled = Bitmap.createScaledBitmap(bmp, (int)(bmp.getWidth() * parentXscale), (int)(bmp.getHeight() * parentYscale), false);
        this.setImageBitmap(scaled);

        myLayoutParams.leftMargin = parentLayoutParams.leftMargin + (int)x;
        myLayoutParams.topMargin = parentLayoutParams.topMargin + (int)y;
        this.setLayoutParams(myLayoutParams);
    }
    public void setPosition(PointF pos){
        this.setPosition(pos.x, pos.y);
        if (this.photoContent != null)
            this.photoContent.setPosition(pos);
    }
    public void move(float dx, float dy){
        RelativeLayout.LayoutParams myLayoutParams = (RelativeLayout.LayoutParams) this.getLayoutParams();

        myLayoutParams.leftMargin = myLayoutParams.leftMargin + (int)dx;
        myLayoutParams.topMargin = myLayoutParams.topMargin + (int)dy;
        this.setLayoutParams(myLayoutParams);
    }
    public void move(PointF dPos){
        this.move(dPos.x, dPos.y);
    }

    public void setImageFromContent(PhotoContent photoContent, ImageView parentImage){
        this.parentImageView = parentImage;
        this.setImageBitmap(photoContent.getBitmap());
        this.photoContent = photoContent;
        //this.setPosition(photoContent.getPosition());
        this.setMovableViewListener();
    }

    public MovableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setMovableViewListener(){
        this.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case android.view.MotionEvent.ACTION_DOWN:
                        Log.d("TouchTest", "Touch down");
                        x = event.getX();
                        y = event.getY();
                        break;
                    case android.view.MotionEvent.ACTION_UP:
                        Log.d("TouchTest", "Touch up");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float newX = event.getX();
                        float newY = event.getY();
                        float dx = newX - x;
                        float dy = newY - y;
                        Log.d("TouchTest", "Move " + dy);
                        if (Math.abs(dx) > 0f || Math.abs(dy) > 0) {
                            x = newX;
                            y = newY;
                            ((MovableImageView) view).move(dx, dy);
                        }
                        break;
                }
                return true;
            }
        });
    }

    public ImageView getParentImageView() {
        return parentImageView;
    }

    public void setParentImageView(ImageView parentImageView) {
        this.parentImageView = parentImageView;
    }

    public Bitmap getContentBitmap(){
        return photoContent.getBitmap();
    }
}
