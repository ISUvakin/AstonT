import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestComparator {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void testFirstGreater() {
        provideInput("15\n10\n");
        MainComparator.main(new String[]{});
        assertOutputContains("15 > 10");
    }

    @Test
    void testEqual() {
        provideInput("10\n10\n");
        MainComparator.main(new String[]{});
        assertOutputContains("10 = 10");
    }

    @Test
    void testFirstLess() {
        provideInput("5\n10\n");
        MainComparator.main(new String[]{});
        assertOutputContains("5 < 10");
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void assertOutputContains(String expected) {
        assertTrue(outputStream.toString().contains(expected));
    }
}
