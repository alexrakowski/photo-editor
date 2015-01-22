package projekt_android.photoeditor;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;

import projekt_android.photoeditor.activities.MainMenu;
import projekt_android.photoeditor.activities.MoveContent;

/**
 * Created by Piotrek on 2015-01-21.
 */
public class MoveContentTest extends ActivityUnitTestCase<MoveContent> {

    private Button finishMovingContentButton;

    private MoveContent moveContent;

    public MoveContentTest() {
        super(MoveContent.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(getInstrumentation().getTargetContext(),
                MoveContent.class);
        startActivity(intent, null, null);
        moveContent = getActivity();
    }

    public void testFinishMovingButtonLayout() {
        int buttonId = R.id.finishMovingContentButton;
        assertNotNull("Button not found", moveContent.findViewById(buttonId));
        finishMovingContentButton = (Button) moveContent.findViewById(buttonId);
        assertEquals("Button label is incorrect", "Finish", finishMovingContentButton.getText());
    }

    public void testFinishIntent() {
        finishMovingContentButton = (Button) moveContent.findViewById(R.id.finishMovingContentButton);
        finishMovingContentButton.performClick();

        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", triggeredIntent);

        assertEquals("Intent should start correct class", "projekt_android.photoeditor.activities.PhotoEditionConfirmal", triggeredIntent.getComponent().getClassName());
    }

}
