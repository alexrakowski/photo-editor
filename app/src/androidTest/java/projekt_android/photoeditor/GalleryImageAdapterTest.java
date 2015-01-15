package projekt_android.photoeditor;

import android.test.InstrumentationTestCase;

import java.util.ArrayList;
import java.util.List;

import projekt_android.photoeditor.adapters.GalleryImageAdapter;

/**
 * Created by Piotrek on 2014-12-18.
 */
public class GalleryImageAdapterTest extends InstrumentationTestCase {

    private static final String NON_EXISTENT_FILENAME = "filewhichdoesntexist.extension";

    private static final int WIDTH = 100;

    private List<String> urls;

    private GalleryImageAdapter testAdapter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        urls = new ArrayList<String>();
        urls.add(NON_EXISTENT_FILENAME);
        testAdapter = new GalleryImageAdapter(urls, getInstrumentation().getContext(), WIDTH);
    }

    public void testFilterUrls() {
        assertEquals("Adapter should filter non-existent files", 0, testAdapter.getUrls().size());
    }

}
