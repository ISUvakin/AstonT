import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestFactorialCalculator {
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
    void testFactorialOfFive() {
        provideInput("5\n");
        FactorialCalculator.main(new String[]{});
        assertOutputContains("5! = 120");
    }

    @Test
    void testFactorialOfZero() {
        provideInput("0\n");
        FactorialCalculator.main(new String[]{});
        assertOutputContains("0! = 1");
    }

    @Test
    void testNegativeNumber() {
        provideInput("-5\n");
        
        FactorialCalculator.main(new String[]{});
        assertOutputContains("Факториал определен только для неотрицательных чисел");
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void assertOutputContains(String expected) {
        assertTrue(outputStream.toString().contains(expected));
    }
}