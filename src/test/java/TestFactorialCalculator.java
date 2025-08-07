import org.testng.annotations.*;
import java.io.*;
import static org.testng.Assert.*;

public class TestFactorialCalculator {
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
    public void testFactorialOf5() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainFactorialCalculator.main(new String[]{});
        assertTrue(outContent.toString().contains("5! = 120"));
    }

    @Test
    public void testFactorialOf0() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainFactorialCalculator.main(new String[]{});
        assertTrue(outContent.toString().contains("0! = 1"));
    }

    @Test
    public void testNegativeInput() {
        String input = "-5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainFactorialCalculator.main(new String[]{});
        assertTrue(outContent.toString().contains("Факториал определен только для неотрицательных чисел"));
    }
}