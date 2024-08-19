# Use the official Jenkins LTS image
FROM jenkins/jenkins:lts

# Skip the initial setup wizard
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Pre-install Jenkins plugins using the Plugin Installation Manager CLI
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy the Groovy scripts for seed job and Maven configuration
COPY seed-job.groovy /usr/share/jenkins/ref/init.groovy.d/seed-job.groovy
COPY configure-maven.groovy /usr/share/jenkins/ref/init.groovy.d/configure-maven.groovy

# Use the existing jenkins user
USER jenkins
