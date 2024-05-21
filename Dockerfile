# Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jre-slim
FROM openjdk:17-slim
# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the host to the container
COPY build/libs/distributeddatabase-0.0.1-SNAPSHOT.jar /app/distributeddatabase-0.0.1-SNAPSHOT.jar
# Replace "target/your-app.jar" with the path to your JAR file

# Make the container listen on port 8080
EXPOSE 8081

# Run the JAR file
CMD ["java", "-jar", "distributeddatabase-0.0.1-SNAPSHOT.jar"]