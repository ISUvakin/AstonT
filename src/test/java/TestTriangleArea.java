import org.testng.annotations.*;
import java.io.*;
import static org.testng.Assert.*;

public class TestTriangleArea {
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
    public void testRealTriangle() {
        String input = "3\n4\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainTriangleArea.main(new String[]{});
        assertTrue(outContent.toString().contains("Площадь треугольника: 6,00"));
    }

    @Test
    public void testInvalidTriangle() {
        String input = "1\n1\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainTriangleArea.main(new String[]{});
        assertTrue(outContent.toString().contains("Треугольник с такими сторонами не существует"));
    }

    @Test
    public void testNonPositiveSide() {
        String input = "-2\n3\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        MainTriangleArea.main(new String[]{});
        assertTrue(outContent.toString().contains("Длины сторон должны быть положительными числами"));
    }
}