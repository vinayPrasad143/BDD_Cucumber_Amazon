# Use Maven with JDK 17 (or your preferred version)
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy project files
COPY . /app

# Run Maven tests
CMD ["mvn", "clean", "test"]
