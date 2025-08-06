# Use Maven with JDK 17 (or your preferred version)
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Install Chrome browser
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    curl \
    gnupg \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' \
    && apt-get update && apt-get install -y google-chrome-stable

# Download ChromeDriver that matches Chrome version
RUN CHROME_VERSION=$(google-chrome --version | grep -oP '\d+\.\d+\.\d+') && \
    DRIVER_VERSION=$(curl -s "https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json" | \
    grep -B1 $CHROME_VERSION | grep version | head -n1 | cut -d '"' -f4) && \
    wget https://storage.googleapis.com/chrome-for-testing-public/$DRIVER_VERSION/linux64/chromedriver-linux64.zip && \
    unzip chromedriver-linux64.zip && \
    mv chromedriver-linux64/chromedriver /usr/bin/chromedriver && \
    chmod +x /usr/bin/chromedriver && \
    rm -rf chromedriver-linux64*

# Set the working directory inside the container
WORKDIR /app

# Copy project files
COPY . /app

# Run Maven tests
CMD ["mvn", "clean", "test"]
