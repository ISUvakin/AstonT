import org.testng.annotations.*;
import java.io.*;
import static org.testng.Assert.*;

public class TestComparator {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeMethod
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterMethod
    public void restoreStreams() {
        System.setOut(originalOut);
        outContent.reset();
    }

    @Test
    public void testNum1IsGreater() {
        String input = "14\n9\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainComparator.main(new String[]{});
        assertTrue(outContent.toString().contains("14 > 9"));
    }

    @Test
    public void testNum2IsGreater() {
        String input = "8\n12\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainComparator.main(new String[]{});
        assertTrue(outContent.toString().contains("8 < 12"));
    }

    @Test
    public void testEqual() {
        String input = "6\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainComparator.main(new String[]{});
        assertTrue(outContent.toString().contains("6 = 6"));
    }
}