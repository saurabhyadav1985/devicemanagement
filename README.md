# Device Management
Device management service for acme (dummy firm). this is a device management application that allows you to add, retrieve, update, delete, and search for devices. It is a typical demo of a Spring Boot application with a RESTful API and an in-memory database.

# Setup 
To start the application, you need to follow these steps:

1. **Build the application**: Open a terminal and navigate to the root directory of the project. Run the following command to build the application using Maven:

```bash
mvn clean install
```

2. **Run the application**: After the build is successful, you can run the application using the following command:

```bash
mvn spring-boot:run
```

The application will start and by default, it will run on port 8080.

3. **Access the application**: You can access the application by opening a web browser and navigating to `http://localhost:8080`.

Please note that these instructions assume that you have Maven and Java installed on your machine. If not, you need to install them first.

4. **Build the Docker image**: After the build is successful, you can build your Docker image. 

Make sure your Dockerfile is in the root directory of your project and DeviceManagement binary is generated in target directory. Run the following command to build your Docker image:

docker build -t device-management-app .

5. **Push the Docker image to Docker Hub**: After the build is successful, you can push your Docker image to Docker Hub.
docker tag device-management-app:latest yourusername/device-management-app:latest
docker push yourusername/device-management-app:latest

6. **Run the Docker image**: After the push is successful, you can run your Docker image.
docker run -p 8080:8080 device-management-app:latest

## H2 UI
Access the H2 Database UI at: http://localhost:8080/h2-console

## API

Here are the `curl` commands for each of the API endpoints in your `DeviceController`:

1. **Add a new device** (POST /devices):
```bash
curl -X POST -H "Content-Type: application/json" -d '{"brand":"BrandName", "name":"ModelName"}' http://localhost:8080/devices
```
Replace `"BrandName"` and `"ModelName"` with the actual brand and model of the device.

2. **Get a device by ID** (GET /devices/{id}):
```bash
curl -X GET http://localhost:8080/devices/1
```
Replace `1` with the actual ID of the device.

3. **Get all devices** (GET /devices):
```bash
curl -X GET http://localhost:8080/devices
```

4. **Update a device** (PUT /devices):
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"id":1, "brand":"NewBrandName", "name":"NewModelName"}' http://localhost:8080/devices
```
Replace `1`, `"NewBrandName"`, and `"NewModelName"` with the actual ID, new brand, and new model of the device.

5. **Delete a device** (DELETE /devices/{id}):
```bash
curl -X DELETE http://localhost:8080/devices/1
```
Replace `1` with the actual ID of the device.

6. **Search devices by brand** (GET /devices/search):
```bash
curl -X GET 'http://localhost:8080/devices/search?brand=BrandName'
```
Replace `"BrandName"` with the actual brand name you want to search for.

Please note that these `curl` commands assume that your server is running on `localhost` and port `8080`. If your server is running on a different host or port, please replace `localhost:8080` with the correct host and port.