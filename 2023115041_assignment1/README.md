## Distributed Weather Monitoring Using RPC and RMI

### Overview
This project implements a **Weather Monitoring System** using two distributed communication technologies:
- **Remote Procedure Call (RPC)** with Python (XML-RPC)
- **Remote Method Invocation (RMI)** with Java

Both services are intended to run on a cloud virtual machine (e.g., Ubuntu VM on a cloud provider) and are accessed remotely by client programs running on a local machine.

The goal is to demonstrate successful client–server communication, remote invocation of weather-related operations, and correct retrieval of weather conditions from a cloud-hosted server.

---

## RPC Weather Service (Python)

### Description
A **Weather Monitoring Service** is implemented using Python XML-RPC.  
The server maintains simple weather records for different cities and evaluates the current weather condition.

### Features
- **Register or update weather data** (temperature and humidity) for a city
- **Retrieve stored weather data** for a city
- **Classify weather condition** (e.g., *Hot and Dry*, *Humid*, *Cold*, *Moderate*)

### Files
- `rpc_server.py` – XML-RPC server exposing weather methods and running on the cloud VM
- `rpc_client.py` – RPC client that connects to the remote server and calls weather operations

### Example Operations
For each city (e.g., Chennai, Delhi, Dubai), the client:
- Sends temperature and humidity to `update_weather`
- Retrieves data using `get_record`
- Gets a human-readable condition from `condition_status`

---

## RMI Weather Service (Java)

### Description
A **Weather Monitoring Service** is also implemented using Java RMI.  
The remote object stores weather data for cities and evaluates weather conditions based on temperature and humidity.

### Features
- **updateWeather(city, temperature, humidity)** – register or update weather data for a city
- **getWeather(city)** – retrieve the stored weather record for a city
- **conditionStatus(city)** – return a textual description of the current weather condition

### Files
- `studservprog.java` – defines the `WeatherService` remote interface
- `WeatherServiceImpl.java` – implements the `WeatherService` interface and contains the server-side logic
- `RMI_clientprog.java` – RMI client that looks up the remote object and invokes its methods

### Behaviour
The RMI client:
- Connects to the RMI registry on the cloud VM (`EC2_PUBLIC_IP`, port `1099`)
- Looks up the remote `WeatherService` object
- Invokes `updateWeather`, `getWeather`, and `conditionStatus` for sample cities (e.g., Chennai, Delhi)

---

## Execution Environment
- **Server side**
  - Python XML-RPC server (`rpc_server.py`) running on a cloud VM (listening on port `8000`)
  - Java RMI server hosting `WeatherServiceImpl` and binding it in the RMI registry (default port `1099`)
- **Client side**
  - Python RPC client (`rpc_client.py`) connecting to the VM’s public IP
  - Java RMI client (`RMI_clientprog.java`) connecting to the same public IP and RMI registry

Make sure to replace `EC2_PUBLIC_IP` in the client files with the actual public IP address of your cloud VM before running.

---

## Conclusion
This weather monitoring project demonstrates:
- Remote communication using **RPC (Python XML-RPC)** and **RMI (Java)**
- Cloud-based deployment of distributed weather services
- Correct client–server interaction and retrieval of weather conditions for multiple cities

It can be extended with additional parameters (e.g., wind speed, pressure) or persistence mechanisms (e.g., database storage) to build a richer weather monitoring platform.
