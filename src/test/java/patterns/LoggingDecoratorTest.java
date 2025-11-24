package patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class LoggingDecoratorTest {

    @Test
    void testLoggingFind() {
        SimpleDataService simpleService = new SimpleDataService();
        LoggingDecorator loggingService = new LoggingDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        loggingService.findDataByKey("testKey");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Поиск данных по ключу: testKey"));
    }

    @Test
    void testLoggingSave() {
        SimpleDataService simpleService = new SimpleDataService();
        LoggingDecorator loggingService = new LoggingDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        loggingService.saveData("key", "data");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Сохранение данных. Ключ: key, Данные: data"));
    }

    @Test
    void testLoggingDelete() {
        SimpleDataService simpleService = new SimpleDataService();
        LoggingDecorator loggingService = new LoggingDecorator(simpleService);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        loggingService.deleteData("testKey");

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Удаление данных по ключу: testKey"));
    }
}