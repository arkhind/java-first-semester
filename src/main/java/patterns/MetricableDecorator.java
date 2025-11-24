package patterns;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class MetricableDecorator implements DataService {
    private final DataService wrapped;
    private final MetricService metricService = new MetricService();

    public MetricableDecorator(DataService wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        Instant start = Instant.now();
        Optional<String> result = wrapped.findDataByKey(key);
        metricService.sendMetric(Duration.between(start, Instant.now()));
        return result;
    }

    @Override
    public void saveData(String key, String data) {
        Instant start = Instant.now();
        wrapped.saveData(key, data);
        metricService.sendMetric(Duration.between(start, Instant.now()));
    }

    @Override
    public boolean deleteData(String key) {
        Instant start = Instant.now();
        boolean result = wrapped.deleteData(key);
        metricService.sendMetric(Duration.between(start, Instant.now()));
        return result;
    }

    public static class MetricService {
        public void sendMetric(Duration duration) {
            System.out.println("Метод выполнялся: " + duration.toString());
        }
    }
}