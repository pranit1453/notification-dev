# Build Stage
FROM eclipse-temurin:25-jdk AS builder
WORKDIR /app
# Copy Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY settings.gradle .
COPY build.gradle .
RUN chmod +x gradlew
# Copy source
COPY src src
# Build Spring Boot executable jar
RUN ./gradlew clean bootJar --no-daemon

# Runtime Stage
FROM eclipse-temurin:25-jre
WORKDIR /app
RUN useradd -ms /bin/bash spring
COPY --from=builder /app/build/libs/*.jar app.jar
USER spring
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]