package projekt_android.photoeditor;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import projekt_android.photoeditor.activities.Gallery;
import projekt_android.photoeditor.activities.MainMenu;
import projekt_android.photoeditor.activities.MoveContent;
import projekt_android.photoeditor.activities.PhotoEditionConfirmal;

/**
 * Created by Piotrek on 2015-01-22.
 */
public class MoveContentIntegrationTest  extends ActivityInstrumentationTestCase2<MoveContent> {

    private MoveContent moveContentActivity;

    public MoveContentIntegrationTest() {
        super(MoveContent.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        moveContentActivity = getActivity();
    }

    public void testStartPhotoEditionConfirmal() {

        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(PhotoEditionConfirmal.class.getName(), null, false);

        final Button finishMovingButton = (Button) moveContentActivity.findViewById(R.id.finishMovingContentButton);

        assertNotNull(finishMovingButton);

        moveContentActivity.runOnUiThread(new Runnable() {
            public void run() {
                finishMovingButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

        PhotoEditionConfirmal confrimal = (PhotoEditionConfirmal) monitor.waitForActivityWithTimeout(10 * 1000);
        assertNotNull("Gallery should start", confrimal);


    }

}
