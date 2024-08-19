# Use the official Jenkins LTS image
FROM jenkins/jenkins:lts

# Skip the initial setup wizard
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Pre-install Jenkins plugins using the Plugin Installation Manager CLI
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Copy the seed job (build pipeline) configuration
COPY seed-job.groovy /usr/share/jenkins/ref/init.groovy.d/seed-job.groovy

# You can keep using the existing jenkins user (no need to recreate it)
USER jenkins
