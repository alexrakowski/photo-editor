package projekt_android.photoeditor;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.UiThreadTest;
import android.widget.Button;

import projekt_android.photoeditor.activities.Gallery;
import projekt_android.photoeditor.activities.MainMenu;

/**
 * Created by Piotrek on 2015-01-21.
 */
public class MainMenuIntegrationTest extends ActivityInstrumentationTestCase2<MainMenu> {

    private MainMenu mainMenuActivity;

    public MainMenuIntegrationTest() {
        super(MainMenu.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mainMenuActivity = getActivity();
    }

    public void testStartGallery() {

        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Gallery.class.getName(), null, false);

        final Button galleryButton = (Button) mainMenuActivity.findViewById(R.id.showGalleryButton);

        assertNotNull(galleryButton);

        mainMenuActivity.runOnUiThread(new Runnable() {
            public void run() {
                galleryButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

        Gallery gallery = (Gallery) monitor.waitForActivityWithTimeout(10 * 1000);
        assertNotNull("Gallery should start", gallery);


    }
}
