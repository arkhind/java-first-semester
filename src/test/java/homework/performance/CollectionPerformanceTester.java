package homework.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CollectionPerformanceTester {

    private static final int ELEMENT_COUNT = 10000;
    private static final int WARMUP_ITERATIONS = 5;

    public static void main(String[] args) {
        testPerformance();
    }

    public static void testPerformance() {
        // Warm-up JIT
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            runTests(new ArrayList<>(), new LinkedList<>(), false);
        }

        List<String> results = runTests(new ArrayList<>(), new LinkedList<>(), true);
        printResults(results);
    }

    private static List<String> runTests(List<Integer> arrayList, List<Integer> linkedList, boolean shouldPrint) {
        List<String> results = new ArrayList<>();

        if (shouldPrint) results.add("Операция;ArrayList (ms);LinkedList (ms)");

        results.add(measureOperation("Добавление в конец", arrayList, linkedList, CollectionPerformanceTester::addEnd, shouldPrint));
        results.add(measureOperation("Добавление в начало", arrayList, linkedList, CollectionPerformanceTester::addStart, shouldPrint));
        results.add(measureOperation("Вставка в середину", arrayList, linkedList, CollectionPerformanceTester::insertMiddle, shouldPrint));
        results.add(measureOperation("Доступ по индексу", arrayList, linkedList, CollectionPerformanceTester::getIndex, shouldPrint));
        results.add(measureOperation("Удаление из середины", arrayList, linkedList, CollectionPerformanceTester::removeIndex, shouldPrint));
        results.add(measureOperation("Удаление из конца", arrayList, linkedList, CollectionPerformanceTester::removeEnd, shouldPrint));

        return results;
    }

    private static String measureOperation(String opName, List<Integer> list1, List<Integer> list2, Operation op, boolean shouldPrint) {
        long time1 = op.measure(list1);
        long time2 = op.measure(list2);

        if (shouldPrint) {
            return String.format("%s;%d;%d", opName, time1, time2);
        }
        return null;
    }

    @FunctionalInterface
    private interface Operation {
        long measure(List<Integer> list);
    }

    private static long measureTime(Runnable action) {
        long start = System.nanoTime();
        action.run();
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long addEnd(List<Integer> list) {
        list.clear();
        return measureTime(() -> {
            for (int i = 0; i < ELEMENT_COUNT; i++) {
                list.add(i);
            }
        });
    }

    private static long addStart(List<Integer> list) {
        list.clear();
        return measureTime(() -> {
            for (int i = 0; i < ELEMENT_COUNT; i++) {
                list.add(0, i);
            }
        });
    }

    private static long insertMiddle(List<Integer> list) {
        list.clear();
        for (int i = 0; i < ELEMENT_COUNT; i++) list.add(0);
        int middle = ELEMENT_COUNT / 2;
        return measureTime(() -> list.add(middle, 1));
    }

    private static long getIndex(List<Integer> list) {
        list.clear();
        for (int i = 0; i < ELEMENT_COUNT; i++) list.add(i);
        return measureTime(() -> {
            for (int i = 0; i < ELEMENT_COUNT; i++) {
                list.get(i);
            }
        });
    }

    private static long removeIndex(List<Integer> list) {
        list.clear();
        for (int i = 0; i < ELEMENT_COUNT; i++) list.add(i);
        int middle = ELEMENT_COUNT / 2;
        return measureTime(() -> list.remove(middle));
    }

    private static long removeEnd(List<Integer> list) {
        list.clear();
        for (int i = 0; i < ELEMENT_COUNT; i++) list.add(i);
        return measureTime(() -> list.remove(ELEMENT_COUNT - 1));
    }

    private static void printResults(List<String> results) {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Анализ производительности (N=" + ELEMENT_COUNT + ")");
        System.out.println("------------------------------------------------------------------");

        for (String line : results) {
            String[] parts = line.split(";");
            if (parts.length == 3) {
                System.out.printf("| %-20s | %-15s | %-15s |\n", parts[0], parts[1] + " ms", parts[2] + " ms");
            } else {
                System.out.printf("| %-20s | %-15s | %-15s |\n", parts[0], "ArrayList (ms)", "LinkedList (ms)");
            }
            if (parts[0].equals("Операция")) {
                System.out.println("|----------------------|-----------------|-----------------|");
            }
        }
        System.out.println("------------------------------------------------------------------");
    }
}