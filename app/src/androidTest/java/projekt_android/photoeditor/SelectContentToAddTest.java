package projekt_android.photoeditor;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.UiThreadTest;
import android.widget.Button;

import projekt_android.photoeditor.activities.MainMenu;
import projekt_android.photoeditor.activities.SelectContentToAdd;

/**
 * Created by Piotrek on 2015-01-22.
 */
public class SelectContentToAddTest extends ActivityUnitTestCase<SelectContentToAdd> {

    private Button confirmContentButton;

    private SelectContentToAdd activity;

    public SelectContentToAddTest() {
        super(SelectContentToAdd.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                SelectContentToAdd.class);
        startActivity(intent, null, null);
        activity = getActivity();
    }

    public void testConfirmButtonLayout() {
        int buttonId = R.id.confirmContentButton;
        assertNotNull("Button not found", activity.findViewById(buttonId));
        confirmContentButton = (Button) activity.findViewById(buttonId);
        assertEquals("Button label is incorrect", "I'm ready", confirmContentButton.getText());
    }

    public void testMoveContentIntent() {
        confirmContentButton = (Button) activity.findViewById(R.id.confirmContentButton);
        confirmContentButton.performClick();

        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);

        assertEquals("Intent should start correct class", "projekt_android.photoeditor.activities.MoveContent", triggeredIntent.getComponent().getClassName());
    }
}
