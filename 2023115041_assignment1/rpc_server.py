"""
RPC SERVER
----------
Remote Weather Monitoring Service
Hosted on Cloud VM (EC2)

This server allows clients to:
- Register weather data for a city
- Retrieve weather records
- Check weather condition status
"""

from xmlrpc.server import SimpleXMLRPCServer

class WeatherService:
    """Service class that manages weather records."""

    def __init__(self):
        # Dictionary structure:
        # { city: {"temperature": temp, "humidity": hum} }
        self.records = {}

    def update_weather(self, city, temperature, humidity):
        """
        Register or update weather data for a city.
        """
        self.records[city] = {
            "temperature": temperature,
            "humidity": humidity
        }
        return f"Weather data updated for {city}"

    def get_record(self, city):
        """
        Retrieve weather record for a given city.
        """
        if city not in self.records:
            return "No record found for this city."
        return self.records[city]

    def condition_status(self, city):
        """
        Check weather condition based on temperature and humidity.
        """
        if city not in self.records:
            return "No record found for this city."

        temp = self.records[city]["temperature"]
        hum = self.records[city]["humidity"]

        if temp > 35 and hum < 40:
            return "Hot and Dry"
        elif temp > 30 and hum >= 60:
            return "Humid"
        elif temp < 15:
            return "Cold"
        else:
            return "Moderate"

# --- Server Setup ---
server = SimpleXMLRPCServer(("0.0.0.0", 8000), allow_none=True)
server.register_instance(WeatherService())

print("RPC Weather Monitoring Service running on port 8000...")
server.serve_forever()