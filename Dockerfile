# Base image with Java & Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Optional: Resolve dependencies first (for caching)
RUN mvn dependency:resolve

# Package the tests (but skip tests to avoid running them now)
RUN mvn clean install -DskipTests

# =====================
# Runtime image
# =====================
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Install Chrome (new stable version)
RUN apt-get update && apt-get install -y wget gnupg2 unzip \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Set Chrome as default display size (needed for headless)
ENV CHROME_BIN=/usr/bin/google-chrome
ENV DISPLAY=:99

# Copy built project from builder
COPY --from=builder /app /app

# Run tests using Chrome in headless mode
CMD ["mvn", "test", "-Dbrowser=chrome"]