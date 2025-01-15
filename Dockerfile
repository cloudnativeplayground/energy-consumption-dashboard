# Step 1: Use an official OpenJDK base image
FROM openjdk:17-jdk-slim as build

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Maven pom.xml and install dependencies
COPY pom.xml .

# Download dependencies (avoid copying the entire source code initially to leverage Docker's cache)
RUN mvn dependency:go-offline

# Step 4: Copy the rest of the application source code
COPY src ./src

# Step 5: Build the application with Maven
RUN mvn clean package -DskipTests

# Step 6: Create a smaller runtime image to reduce the size
FROM openjdk:17-jdk-slim

# Step 7: Set the working directory for the final image
WORKDIR /app

# Step 8: Copy the built JAR file from the build stage to the runtime image
COPY --from=build /app/target/energy-consumption-dashboard-0.0.1-SNAPSHOT.jar /app/energy-consumption-dashboard.jar

# Step 9: Expose the port your application will run on
EXPOSE 8080

# Step 10: Define the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app/energy-consumption-dashboard.jar"]

# Step 11: Optionally, set an environment variable for JVM options
# ENV JAVA_OPTS="-Xms512m -Xmx1024m"

