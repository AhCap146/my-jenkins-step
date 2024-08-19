This project provides a custom Jenkins Docker image pre-configured with essential plugins, a seed job for a pipeline, and Maven configuration.

uild the Docker Image
To build the Docker image, run the following command in the root directory of this project:

sh
docker build -t custom-jenkins:latest .
This command will build the Docker image and tag it as custom-jenkins:latest.

Run the Docker Container
To run the Jenkins container:

sh
docker run -d -p 8080:8080 -p 50000:50000 --name custom-jenkins custom-jenkins:latest
The Jenkins web UI will be accessible on port 8080.
The Jenkins agent (slave) connections will be on port 50000.
Access Jenkins
Once the container is running, you can access Jenkins by navigating to:


http://localhost:8080
The Jenkins home directory is configured to persist data, so even if the container is stopped or removed, your Jenkins data will be preserved.