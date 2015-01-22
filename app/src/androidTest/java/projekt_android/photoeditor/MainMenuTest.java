package projekt_android.photoeditor;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import projekt_android.photoeditor.activities.MainMenu;
import projekt_android.photoeditor.activities.SelectContentToAdd;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class MainMenuTest extends ActivityUnitTestCase<MainMenu> {

    private Button convertButton;

    private Button galleryButton;

    private MainMenu mainMenuActivity;

    public MainMenuTest() {
        super(MainMenu.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MainMenu.class);
        startActivity(intent, null, null);
        mainMenuActivity = getActivity();
    }

    public void testStartButtonLayout() {
        int buttonId = R.id.convertButton;
        assertNotNull("Button not found", mainMenuActivity.findViewById(buttonId));
        convertButton = (Button) mainMenuActivity.findViewById(buttonId);
        assertEquals("Button label is incorrect", "Start!", convertButton.getText());
    }

    public void testGalleryButtonLayout() {
        int buttonId = R.id.showGalleryButton;
        assertNotNull("Button not found", mainMenuActivity.findViewById(buttonId));
        galleryButton = (Button) mainMenuActivity.findViewById(buttonId);
        assertEquals("Button label is incorrect", "Show Gallery", galleryButton.getText());
    }

    public void testGalleryIntent() {
        galleryButton = (Button) mainMenuActivity.findViewById(R.id.showGalleryButton);
        galleryButton.performClick();

        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);

        assertEquals("Intent should start correct class", "projekt_android.photoeditor.activities.Gallery", triggeredIntent.getComponent().getClassName());
    }

}
