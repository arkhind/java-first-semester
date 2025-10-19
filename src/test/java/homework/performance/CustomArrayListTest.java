package homework.performance;

import custom.collections.CustomArrayList;
import custom.collections.CustomList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    private CustomList<String> list;

    @BeforeEach
    void setUp() {
        list = new CustomArrayList<>();
    }

    @Test
    void testAddAndSize() {
        assertTrue(list.isEmpty());
        assertTrue(list.add("A"));
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.add("B");
        assertEquals(2, list.size());
    }

    @Test
    void testGet() {
        list.add("First");
        list.add("Second");
        assertEquals("First", list.get(0));
        assertEquals("Second", list.get(1));
    }

    @Test
    void testGetOutOfBounds() {
        list.add("Only");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void testRemoveMiddleElement() {
        list.add("A");
        list.add("B");
        list.add("C");
        assertEquals("B", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
    }

    @Test
    void testRemoveFirstElement() {
        list.add("A");
        list.add("B");
        assertEquals("A", list.remove(0));
        assertEquals(1, list.size());
        assertEquals("B", list.get(0));
    }

    @Test
    void testRemoveLastElement() {
        list.add("A");
        list.add("B");
        assertEquals("B", list.remove(1));
        assertEquals(1, list.size());
        assertEquals("A", list.get(0));
    }

    @Test
    void testRemoveOutOfBounds() {
        list.add("One");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    void testDynamicResizing() {
        for (int i = 0; i < 15; i++) {
            list.add("Item" + i);
        }
        assertEquals(15, list.size());
        assertEquals("Item14", list.get(14));
    }

    @Test
    void testIterator() {
        list.add("X");
        list.add("Y");
        list.add("Z");

        int count = 0;
        String[] expected = {"X", "Y", "Z"};
        for (String item : list) {
            assertEquals(expected[count++], item);
        }
        assertEquals(3, count);
    }

    @Test
    void testIteratorNoSuchElement() {
        list.add("Test");
        var iterator = list.iterator();
        iterator.next();
        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }
}