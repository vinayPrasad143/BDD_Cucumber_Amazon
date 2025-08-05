# Use Maven image with JDK 17 (can be changed to JDK 11 if needed)
FROM maven:3.8.8-openjdk-17

# Set working directory inside the container
WORKDIR /app

# Copy only pom.xml to leverage Docker layer caching
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy the entire project to the container
COPY . .

# Build the project using Maven
RUN mvn clean install

# Run the tests when the container starts
CMD ["mvn", "test"]