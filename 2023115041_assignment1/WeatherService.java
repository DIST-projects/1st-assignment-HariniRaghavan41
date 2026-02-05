import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * Remote interface for Weather Monitoring Service.
 * Defines methods to update and query weather information for cities.
 */
public interface WeatherService extends Remote {

        /**
         * Register or update weather data for a given city.
         *
         * @param city        city name
         * @param temperature temperature value
         * @param humidity    humidity value
         * @return status message
         * @throws RemoteException if a remote communication error occurs
         */
        String updateWeather(String city, int temperature, int humidity)
                        throws RemoteException;

        /**
         * Get the stored weather data for a given city.
         *
         * @param city city name
         * @return map containing temperature and humidity (or null if not found)
         * @throws RemoteException if a remote communication error occurs
         */
        Map<String, Object> getWeather(String city)
                        throws RemoteException;

        /**
         * Get a human-readable description of the weather condition for a city.
         *
         * @param city city name
         * @return condition string, e.g., "Hot and Dry", "Humid", "Cold", "Moderate"
         * @throws RemoteException if a remote communication error occurs
         */
        String conditionStatus(String city)
                        throws RemoteException;
}
