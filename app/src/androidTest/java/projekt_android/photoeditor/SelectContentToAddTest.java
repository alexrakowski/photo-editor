package projekt_android.photoeditor;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class SelectContentToAddTest extends ActivityInstrumentationTestCase2<SelectContentToAdd> {

    private Instrumentation.ActivityMonitor activityMonitor;

    private Activity selectContentActivity;

    private Button readyButton;

    public SelectContentToAddTest(Class<SelectContentToAdd> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityMonitor = getInstrumentation().addMonitor(MoveContent.class.getName(), null, false);
        selectContentActivity = getActivity();
        readyButton = (Button)selectContentActivity.findViewById(R.id.confirmContentButton);
    }

    public void testImReady(){
        selectContentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                readyButton.performClick();
            }
        });

        MoveContent nextActivity = (MoveContent) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10);
        assertNotNull("Should start MoveContent activity", nextActivity);
        nextActivity.finish();
    }
}
