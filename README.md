# devicemanagement
Device management service for acme (dummy firm)

## H2 UI : http://localhost:8080/h2-console

## API

Sure, here are the `curl` commands for each of the API endpoints in your `DeviceController`:

1. Add a new device (POST /devices):

```bash
curl -X POST -H "Content-Type: application/json" -d '{"brand":"BrandName", "model":"ModelName"}' http://localhost:8080/devices
```

Replace `"BrandName"` and `"ModelName"` with the actual brand and model of the device.

2. Get a device by ID (GET /devices/{id}):

```bash
curl -X GET http://localhost:8080/devices/1
```

Replace `1` with the actual ID of the device.

3. Get all devices (GET /devices):

```bash
curl -X GET http://localhost:8080/devices
```

4. Update a device (PUT /devices):

```bash
curl -X PUT -H "Content-Type: application/json" -d '{"id":1, "brand":"NewBrandName", "model":"NewModelName"}' http://localhost:8080/devices
```

Replace `1`, `"NewBrandName"`, and `"NewModelName"` with the actual ID, new brand, and new model of the device.

5. Delete a device (DELETE /devices/{id}):

```bash
curl -X DELETE http://localhost:8080/devices/1
```

Replace `1` with the actual ID of the device.

6. Search devices by brand (GET /devices/search):

```bash
curl -X GET http://localhost:8080/devices/search?brand=BrandName
```

Replace `"BrandName"` with the actual brand name you want to search for.

Please note that these `curl` commands assume that your server is running on `localhost` and port `8080`. If your server is running on a different host or port, please replace `localhost:8080` with the correct host and port.

## UPdate and Search not working today