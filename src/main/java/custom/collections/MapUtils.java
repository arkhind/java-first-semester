package custom.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapUtils {

    public static List<Student> findStudentsByGradeRange(Map<Integer, Student> map, double minGrade, double maxGrade) {
        List<Student> result = new ArrayList<>();
        for (Student student : map.values()) {
            if (student.getGrade() >= minGrade && student.getGrade() <= maxGrade) {
                result.add(student);
            }
        }
        return result;
    }

    public static List<Student> getTopNStudents(TreeMap<Integer, Student> map, int n) {
        List<Student> result = new ArrayList<>();

        // Получаем убывающее представление TreeMap, чтобы начать с наибольшего ID
        Map<Integer, Student> descendingMap = map.descendingMap();

        int count = 0;
        for (Map.Entry<Integer, Student> entry : descendingMap.entrySet()) {
            if (count >= n) {
                break;
            }
            result.add(entry.getValue());
            count++;
        }
        return result;
    }
}