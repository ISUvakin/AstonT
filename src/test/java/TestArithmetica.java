import org.testng.annotations.*;
import java.io.*;
import static org.testng.Assert.*;

public class TestArithmetica {
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
    public void testSum() {
        String input = "13\n+\n9\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("13,00 + 9,00 = 22,00"));
    }
    @Test
    public void testDiv() {
        String input = "4\n/\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("4,00 / 2,00 = 2,00"));
    }

    @Test
    public void testMul() {
        String input = "7\n*\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("7,00 * 3,00 = 21,00"));
    }

    @Test
    public void testDivByZero() {
        String input = "4\n/\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("Ошибка: деление на ноль"));
    }

    @Test
    public void testInvalidOperator() {
        String input = "2\nx\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("Неподдерживаемая операция"));
    }

    @Test
    public void testMulMinus() {
        String input = "2\n*\n-4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainArithmetica.main(new String[]{});
        assertTrue(outContent.toString().contains("2,00 * (-4,00) = -8.00"));
    }
}