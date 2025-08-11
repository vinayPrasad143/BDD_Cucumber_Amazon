# ------------------------------
# Stage 1: Build with Maven
# ------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY . .
RUN mvn clean package -DskipTests

# ------------------------------
# Stage 2: Runtime with Chrome + ChromeDriver
# ------------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Install Chrome dependencies
RUN apt-get update && apt-get install -y \
    wget curl unzip gnupg libglib2.0-0 libnss3 libfontconfig1 libxss1 \
    libasound2t64 fonts-liberation libappindicator3-1 libatk-bridge2.0-0 \
    libgtk-3-0 ca-certificates --no-install-recommends && \
    rm -rf /var/lib/apt/lists/*

# Install Google Chrome Stable
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | gpg --dearmor > /usr/share/keyrings/google-linux.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

# Install ChromeDriver matching Chrome version
RUN CHROME_VERSION=$(google-chrome --version | grep -oP '\d+\.\d+\.\d+') && \
    DRIVER_VERSION=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/LATEST_RELEASE_$CHROME_VERSION") && \
    wget -q "https://storage.googleapis.com/chrome-for-testing-public/$DRIVER_VERSION/linux64/chromedriver-linux64.zip" && \
    unzip chromedriver-linux64.zip -d /usr/local/bin/ && \
    rm chromedriver-linux64.zip

# Copy fat jar
COPY --from=builder /app/target/*-shaded.jar /app/tests.jar

# Run TestNG tests
CMD ["java", "-cp", "/app/tests.jar", "org.testng.TestNG", "testng.xml"]