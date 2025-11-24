package patterns;

import java.util.Optional;

public class LoggingDecorator implements DataService {
    private final DataService wrapped;

    public LoggingDecorator(DataService wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        System.out.println("Поиск данных по ключу: " + key);
        return wrapped.findDataByKey(key);
    }

    @Override
    public void saveData(String key, String data) {
        System.out.println("Сохранение данных. Ключ: " + key + ", Данные: " + data);
        wrapped.saveData(key, data);
    }

    @Override
    public boolean deleteData(String key) {
        System.out.println("Удаление данных по ключу: " + key);
        return wrapped.deleteData(key);
    }
}