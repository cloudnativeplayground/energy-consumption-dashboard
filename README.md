Energy Consumption Dashboard
============================

Overview
--------
The **Energy Consumption Dashboard** is an open-source Java application designed to monitor and optimize energy usage. It provides real-time tracking of energy consumption, alerts based on usage patterns, and integrates with **InfluxDB** for storing usage data. The dashboard is built using **Spring Boot**, **Thymeleaf**, and **Kubernetes** for deployment, and it is designed to scale in a cloud-native environment.

Features
--------
- **Real-time energy usage tracking**.
- **Alerting** based on usage thresholds.
- Integration with **InfluxDB** for storing energy usage data.
- UI built with **Thymeleaf** templates.
- **Spring Boot** backend with **Swagger** UI for API documentation.
- Deployed using **Kubernetes** for scalability.
- **Actuator endpoints** for monitoring the application health and performance.

Tech Stack
-----------
- **Backend**: Spring Boot (Java)
- **Database**: InfluxDB
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **API Documentation**: Swagger
- **Deployment**: Kubernetes
- **Monitoring**: Spring Boot Actuator

Prerequisites
-------------
Before running this application, ensure that the following are installed:

- Java 8 or above
- Maven
- Docker (for containerization)
- Kubernetes (for orchestration)
- InfluxDB (for storing energy usage data)

Setup Instructions
------------------

### 1. Clone the Repository
Clone this repository to your local machine:

```bash
git clone https://github.com/cloudnativeplayground/energy-consumption-dashboard.git
cd energy-consumption-dashboard
```

Sure! Here's the **README** file in plain text format:

```
Energy Consumption Dashboard
============================

Overview
--------
The **Energy Consumption Dashboard** is an open-source Java application designed to monitor and optimize energy usage. It provides real-time tracking of energy consumption, alerts based on usage patterns, and integrates with **InfluxDB** for storing usage data. The dashboard is built using **Spring Boot**, **Thymeleaf**, and **Kubernetes** for deployment, and it is designed to scale in a cloud-native environment.

Features
--------
- **Real-time energy usage tracking**.
- **Alerting** based on usage thresholds.
- Integration with **InfluxDB** for storing energy usage data.
- UI built with **Thymeleaf** templates.
- **Spring Boot** backend with **Swagger** UI for API documentation.
- Deployed using **Kubernetes** for scalability.
- **Actuator endpoints** for monitoring the application health and performance.

Tech Stack
-----------
- **Backend**: Spring Boot (Java)
- **Database**: InfluxDB
- **Frontend**: Thymeleaf, HTML, CSS, JavaScript
- **API Documentation**: Swagger
- **Deployment**: Kubernetes
- **Monitoring**: Spring Boot Actuator

Prerequisites
-------------
Before running this application, ensure that the following are installed:

- Java 8 or above
- Maven
- Docker (for containerization)
- Kubernetes (for orchestration)
- InfluxDB (for storing energy usage data)

Setup Instructions
------------------

### 1. Clone the Repository
Clone this repository to your local machine:

```bash
git clone https://github.com/cloudnativeplayground/energy-consumption-dashboard.git
cd energy-consumption-dashboard
```

### 2. Configure InfluxDB
Make sure InfluxDB is installed and running. You can use Docker to start an InfluxDB container:

```bash
docker run -d -p 8086:8086 -e INFLUXDB_DB=energy_usage -e INFLUXDB_ADMIN_USER=user -e INFLUXDB_ADMIN_PASSWORD=password influxdb
```

### 3. Configure Application Properties
Update the `application.properties` file located in `src/main/resources/application.properties` with the appropriate InfluxDB connection details:

```properties
spring.influx.url=http://localhost:8086
spring.influx.username=user
spring.influx.password=password
spring.influx.database=energy_usage
```

### 4. Build and Run the Application
To build and run the application, use the following Maven commands:

#### Build the application:
```bash
mvn clean install
```

#### Run the application:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

### 5. Access the Dashboard
Once the application is running, visit the following URL to access the Energy Consumption Dashboard:

- **Energy Usage Dashboard**: `http://localhost:8080/`
- **Swagger UI for API Docs**: `http://localhost:8080/swagger-ui/`
- **Actuator Health**: `http://localhost:8080/actuator/health`

### 6. Docker and Kubernetes Deployment
To containerize the application, use Docker to build the image:

#### Build Docker Image:
```bash
docker build -t energy-consumption-dashboard .
```

#### Run Docker Container:
```bash
docker run -d -p 8080:8080 energy-consumption-dashboard
```

For Kubernetes, you can create the necessary Kubernetes YAML files (`deployment.yaml`, `service.yaml`, `ingress.yaml`) to deploy the application.

### 7. Actuator Endpoints
Spring Boot Actuator is enabled by default to monitor and manage your application. Below are some of the key endpoints:

- **Health Check**: `http://localhost:8080/actuator/health`
- **Application Info**: `http://localhost:8080/actuator/info`
- **Metrics**: `http://localhost:8080/actuator/metrics`

### 8. Swagger API Documentation
The application uses Swagger for API documentation. After starting the application, you can access the Swagger UI at the following URL:

- **Swagger UI**: `http://localhost:8080/swagger-ui/`

The API documentation will show you the available REST endpoints for interacting with the energy consumption data.

Contributing
------------
Contributions are welcome! If you'd like to improve the project, please follow the steps below:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Open a pull request.

License
-------
This project is licensed under the **GNU General Public License 3.0** - see the [LICENSE](LICENSE) file for details.

Maintainer
----------
The project is maintained by **Aditya Pratap Bhuyan**.  
[LinkedIn Profile](https://linkedin.com/in/adityabhuyan)

---

### Contact
If you have any questions or feedback, feel free to contact the maintainer via the **GitHub issues** page or **LinkedIn**.


### Explanation:

- **Overview**: A brief description of the application.
- **Features**: Highlights of what the app does.
- **Tech Stack**: List of technologies used in the project.
- **Setup Instructions**: Step-by-step guide to clone, configure, and run the application.
- **Swagger**: Provides details about accessing API documentation through Swagger UI.
- **Actuator**: Provides information about health and monitoring endpoints.
- **Docker & Kubernetes**: Instructions for running the app in Docker and deploying it in Kubernetes.
- **Contributing**: Details on how other developers can contribute to the project.
- **License**: Mentions the license under which the project is distributed.



