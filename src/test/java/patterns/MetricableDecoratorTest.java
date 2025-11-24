package patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MetricableDecoratorTest {

    @Test
    void testMetricFind() {
        SimpleDataService simpleService = new SimpleDataService();
        MetricableDecorator metricService = new MetricableDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        metricService.findDataByKey("testKey");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Метод выполнялся: PT"));
    }

    @Test
    void testMetricSave() {
        SimpleDataService simpleService = new SimpleDataService();
        MetricableDecorator metricService = new MetricableDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        metricService.saveData("key", "data");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Метод выполнялся: PT"));
    }

    @Test
    void testMetricDelete() {
        SimpleDataService simpleService = new SimpleDataService();
        MetricableDecorator metricService = new MetricableDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        metricService.deleteData("testKey");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Метод выполнялся: PT"));
    }
}