import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

/**
 * RMI Client
 * Connects to cloud-hosted Weather Monitoring Service
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            // Connect to RMI registry running on EC2 instance
            Registry registry = LocateRegistry.getRegistry("EC2_PUBLIC_IP", 1099);

            // Lookup the remote WeatherService object
            WeatherService service = (WeatherService) registry.lookup("WeatherService");

            // --- Update and query weather data ---
            System.out.println(service.updateWeather("Chennai", 34, 70));

            Map<String, Object> record = service.getWeather("Chennai");
            System.out.println("Weather Record: " + record);

            System.out.println("Condition Status: " + service.conditionStatus("Chennai"));

            // Another city
            System.out.println(service.updateWeather("Delhi", 12, 50));
            System.out.println("Weather Record: " + service.getWeather("Delhi"));
            System.out.println("Condition Status: " + service.conditionStatus("Delhi"));

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}