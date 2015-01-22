package projekt_android.photoeditor;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import projekt_android.photoeditor.activities.Gallery;
import projekt_android.photoeditor.activities.MoveContent;
import projekt_android.photoeditor.activities.SelectContentToAdd;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class SelectContentToAddIntegrationTest extends ActivityInstrumentationTestCase2<SelectContentToAdd> {

    private SelectContentToAdd selectContentActivity;

    public SelectContentToAddIntegrationTest() {
        super(SelectContentToAdd.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        selectContentActivity = getActivity();
    }

    public void testImReady(){

        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MoveContent.class.getName(), null, false);

        final Button readyButton = (Button) selectContentActivity.findViewById(R.id.confirmContentButton);

        assertNotNull(readyButton);

        selectContentActivity.runOnUiThread(new Runnable() {
            public void run() {
                readyButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

        MoveContent nextActivity = (MoveContent) getInstrumentation().waitForMonitorWithTimeout(monitor, 10 * 1000);
        assertNotNull("Should start MoveContent activity", nextActivity);
    }
}
