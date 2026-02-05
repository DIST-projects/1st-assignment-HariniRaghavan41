"""
RPC CLIENT
----------
Connects to cloud-hosted Weather Monitoring Service
"""

import xmlrpc.client

# Connect to the remote Weather Service hosted on EC2
server = xmlrpc.client.ServerProxy("http://EC2_PUBLIC_IP:8000")

# --- Update Weather Data ---
print(server.update_weather("Chennai", 34, 70))
print("Weather Record:", server.get_record("Chennai"))
print("Condition:", server.condition_status("Chennai"))

print(server.update_weather("Delhi", 12, 50))
print("Weather Record:", server.get_record("Delhi"))
print("Condition:", server.condition_status("Delhi"))

print(server.update_weather("Dubai", 40, 25))
print("Weather Record:", server.get_record("Dubai"))
print("Condition:", server.condition_status("Dubai"))