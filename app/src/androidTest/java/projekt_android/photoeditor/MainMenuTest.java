package projekt_android.photoeditor;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import projekt_android.photoeditor.activities.MainMenu;
import projekt_android.photoeditor.activities.SelectContentToAdd;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class MainMenuTest extends ActivityInstrumentationTestCase2<MainMenu> {

    private Instrumentation.ActivityMonitor activityMonitor;

    private Activity selectContentActivity;

    private Button convertButton;

    public MainMenuTest(Class<MainMenu> activityClass) {
        super(activityClass);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityMonitor = getInstrumentation().addMonitor(SelectContentToAdd.class.getName(), null, false);
        selectContentActivity = getActivity();
        convertButton = (Button)selectContentActivity.findViewById(R.id.convertButton);
    }

    public void testConvert(){
        selectContentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                convertButton.performClick();
            }
        });

        SelectContentToAdd nextActivity = (SelectContentToAdd) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 10);
        assertNotNull("Should start MoveContent activity", nextActivity);
        nextActivity.finish();
    }
}
