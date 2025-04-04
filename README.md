# Spring Boot Email Service with MySQL

## Description

This Spring Boot application allows users to send emails with attachments or simple plain text messages. It utilizes MySQL running in a Docker container for data storage. Before using the application, users must enable two-factor authentication (2FA) on their Google accounts to ensure secure email sending.

## Features

- Send emails with attachments (documents, images, etc.)
- Send simple plain text emails
- MySQL database integration
- Dockerized environment for easy setup and deployment

## Prerequisites

Before running the application, ensure you have the following:

- **Docker**: Installed on your machine. You can download it from [Docker's official website](https://docker.com/get-started).
- **Docker Compose**: Included with Docker Desktop installations.
- **Java 11 or higher**: Ensure Java is installed and configured on your system.
- **Google Account**: With two-factor authentication enabled.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/DouglaNyabasa/email_service_spring_boot_docker.git
   cd email_service_spring_boot_docker

   
2.Configure Application Properties:

Open the src/main/resources/application-dev.yml file.
Enter your Google account email and password:
Copy
spring:
  mail:
    username: your_email@gmail.com
    password: your_password
Important: Ensure that you have enabled Less Secure Apps or use an App Password if you have 2FA enabled.
3. Start the Docker Containers:

In the root directory of the project, run:
Copy
docker-compose up -d
This command will start the MySQL container as defined in the docker-compose.yml file.

4. Run the Spring Boot Application:

In a new terminal window, navigate to the project directory and run:
Copy
./mvnw spring-boot:run
If you are using Windows, you might need to use mvnw.cmd instead.

5. Access the Application:

The application will be running on http://localhost:8080.
