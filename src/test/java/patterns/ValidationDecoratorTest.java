package patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

class ValidationDecoratorTest {

    @Test
    void testValidationFind() {
        SimpleDataService simpleService = new SimpleDataService();
        ValidationDecorator validationService = new ValidationDecorator(simpleService);

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.findDataByKey("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.findDataByKey(null);
        });
    }

    @Test
    void testValidationSave() {
        SimpleDataService simpleService = new SimpleDataService();
        ValidationDecorator validationService = new ValidationDecorator(simpleService);

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.saveData("", "data");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.saveData("key", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.saveData(null, "data");
        });
    }

    @Test
    void testValidationDelete() {
        SimpleDataService simpleService = new SimpleDataService();
        ValidationDecorator validationService = new ValidationDecorator(simpleService);

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.deleteData("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            validationService.deleteData(null);
        });
    }

    @Test
    void testValidationSuccess() {
        SimpleDataService simpleService = new SimpleDataService();
        ValidationDecorator validationService = new ValidationDecorator(simpleService);

        assertDoesNotThrow(() -> {
            validationService.saveData("key", "data");
            Optional<String> result = validationService.findDataByKey("key");
            validationService.deleteData("key");
        });
    }
}