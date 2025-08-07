import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestTriangleArea {
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
    void testValidTriangle() {
        provideInput("3\n4\n5\n");
        MainTriangleArea.main(new String[]{});
        assertOutputContains("Площадь треугольника: 6,00");
    }

    @Test
    void testInvalidTriangle() {
        provideInput("1\n1\n3\n");
        MainTriangleArea.main(new String[]{});
        assertOutputContains("Треугольник с такими сторонами не существует");
    }

    @Test
    void testNonPositiveSides() {
        provideInput("-1\n2\n3\n");
        MainTriangleArea.main(new String[]{});
        assertOutputContains("Длины сторон должны быть положительными числами");
    }

    @Test
    void testInputRecoveryAfterInvalidInput() {
        provideInput("invalid\n3\n4\n5\n");
        MainTriangleArea.main(new String[]{});
        assertOutputContains("Ошибка: нужно ввести число");
        assertOutputContains("Площадь треугольника: 6,00");
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    private void assertOutputContains(String expected) {
        assertTrue(outputStream.toString().contains(expected));
    }
}