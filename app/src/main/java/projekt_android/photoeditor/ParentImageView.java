package projekt_android.photoeditor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Alek on 2014-12-15.
 */
public class ParentImageView extends ImageView {
    private ArrayList<MovableImageView> children;

    public void addChild(MovableImageView child){
        if (children == null){
            children = new ArrayList<MovableImageView>();
        }

        children.add(child);
        //child.resetPosition();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        for (MovableImageView child : this.children){
            child.resetPosition();
        }
    }

    public ParentImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        children = new ArrayList<MovableImageView>();
    }
}
