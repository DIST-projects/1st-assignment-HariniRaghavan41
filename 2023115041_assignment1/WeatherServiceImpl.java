import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Remote object implementation for Weather Monitoring Service.
 */
public class WeatherServiceImpl
        extends UnicastRemoteObject
        implements WeatherService {

    // Stores weather records: { city : { "temperature": value, "humidity": value } }
    private Map<String, Map<String, Object>> records;

    protected WeatherServiceImpl() throws RemoteException {
        super();
        records = new HashMap<>();
    }

    @Override
    public String updateWeather(String city, int temperature, int humidity) {
        Map<String, Object> data = new HashMap<>();
        data.put("temperature", temperature);
        data.put("humidity", humidity);
        records.put(city, data);
        return "Weather data updated for " + city;
    }

    @Override
    public Map<String, Object> getWeather(String city) {
        return records.getOrDefault(city, null);
    }

    @Override
    public String conditionStatus(String city) {
        if (!records.containsKey(city)) {
            return "No record found for " + city;
        }

        int temp = (int) records.get(city).get("temperature");
        int hum = (int) records.get(city).get("humidity");

        if (temp > 35 && hum < 40) {
            return "Hot and Dry";
        } else if (temp > 30 && hum >= 60) {
            return "Humid";
        } else if (temp < 15) {
            return "Cold";
        } else {
            return "Moderate";
        }
    }
}