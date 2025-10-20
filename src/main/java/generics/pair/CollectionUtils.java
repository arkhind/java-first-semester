package generics.pair;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    public static <T> List<T> mergeLists(List<? extends T> list1, List<? extends T> list2) {
        List<T> mergedList = new ArrayList<>();
        mergedList.addAll(list1);
        mergedList.addAll(list2);
        return mergedList;
    }

    public static <T> void addAll(List<? super T> destination, List<? extends T> source) {
        destination.addAll(source);
    }
}