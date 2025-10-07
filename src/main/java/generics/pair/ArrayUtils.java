package generics.pair;

public class ArrayUtils {


    public static <T> int findFirst(T[] array, T element) {

        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {

            T currentElement = array[i];

            boolean isMatch;
            if (element == null) {
                isMatch = (currentElement == null);
            } else {
                isMatch = element.equals(currentElement);
            }

            if (isMatch) {
                return i;
            }
        }

        return -1;
    }
}