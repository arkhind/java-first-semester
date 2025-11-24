package patterns;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

class CachingDecoratorTest {

    @Test
    void testCachingFind() {
        SimpleDataService simpleService = new SimpleDataService();
        CachingDecorator cachingService = new CachingDecorator(simpleService);

        simpleService.saveData("key1", "data1");

        Optional<String> result1 = cachingService.findDataByKey("key1");
        Optional<String> result2 = cachingService.findDataByKey("key1");

        assertTrue(result1.isPresent());
        assertEquals("data1", result1.get());
        assertTrue(result2.isPresent());
        assertEquals("data1", result2.get());
    }

    @Test
    void testCachingSave() {
        SimpleDataService simpleService = new SimpleDataService();
        CachingDecorator cachingService = new CachingDecorator(simpleService);

        cachingService.saveData("key1", "data1");

        Optional<String> result = cachingService.findDataByKey("key1");
        assertTrue(result.isPresent());
        assertEquals("data1", result.get());
    }

    @Test
    void testCachingDelete() {
        SimpleDataService simpleService = new SimpleDataService();
        CachingDecorator cachingService = new CachingDecorator(simpleService);

        cachingService.saveData("key1", "data1");
        cachingService.deleteData("key1");

        Optional<String> result = cachingService.findDataByKey("key1");
        assertFalse(result.isPresent());
    }
}