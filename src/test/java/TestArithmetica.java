import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TestArithmetica {
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
    void testSum() {
        provideInput("7\n+\n8\n");
        MainArithmetica.main(new String[]{});
        assertOutputContains("Результат: 7,00 + 8,00 = 15,00");
    }

    @Test
    void testSub() {
        provideInput("4\n-\n5\n");
        MainArithmetica.main(new String[]{});
        assertOutputContains("Результат: 4,00 - 5,00 = -1,00");
    }

    @Test
    void testDivByZero() {
        provideInput("10\n/\n0\n");
        MainArithmetica.main(new String[]{});
        assertOutputContains("Ошибка: деление на ноль");
    }

    @Test
    void testMul() {
        provideInput("2\n*\n2\n");
        MainArithmetica.main(new String[]{});
        assertOutputContains("Результат: 2,00 * 2,00 = 4,00");
    }

    @Test
    void testInvalidOperator() {
        provideInput("10\nx\n5\n");
        MainArithmetica.main(new String[]{});
        assertOutputContains("Неподдерживаемая операция");
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void assertOutputContains(String expected) {
        assertTrue(outputStream.toString().contains(expected));
    }
}