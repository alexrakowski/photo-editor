package projekt_android.photoeditor;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.widget.Button;

import projekt_android.photoeditor.activities.MoveContent;
import projekt_android.photoeditor.activities.PhotoEditionConfirmal;

/**
 * Created by Piotrek on 2015-01-22.
 */
public class PhotoEditionConfirmalTest extends ActivityUnitTestCase<PhotoEditionConfirmal> {

    private Button saveButton;

    private PhotoEditionConfirmal activity;

    public PhotoEditionConfirmalTest() {
        super(PhotoEditionConfirmal.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                PhotoEditionConfirmal.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testSaveButtonLayout() {
        int buttonId = R.id.saveEditedPhotoButton;
        assertNotNull("Button not found", activity.findViewById(buttonId));
        saveButton = (Button) activity.findViewById(buttonId);
        assertEquals("Button label is incorrect", "Save this Photo", saveButton.getText());
    }


}
