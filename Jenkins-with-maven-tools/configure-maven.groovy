import jenkins.model.*
import hudson.tasks.Maven
import hudson.tools.*

println "Starting Maven configuration..."

try {
    // Get the Jenkins instance
    def instance = Jenkins.getInstance()

    // Define Maven installer with the desired Maven version
    def mavenInstaller = new Maven.MavenInstaller("3.9.2")

    // Define the source property for the Maven installation
    def installSourceProperty = new InstallSourceProperty([mavenInstaller])

    // Define Maven installation (name and home directory will be used in jobs)
    def mavenInstallation = new Maven.MavenInstallation("maven-3.9.2", null, [installSourceProperty])

    // Get the existing Maven installations
    def mavenDescriptor = instance.getDescriptorByType(Maven.DescriptorImpl.class)
    def existingInstallations = mavenDescriptor.getInstallations()

    // Add the new Maven installation to the list of existing installations
    def installations = (existingInstallations as List) + mavenInstallation

    // Set the updated list of installations, ensuring it's an array of MavenInstallation
    mavenDescriptor.setInstallations(installations.toArray(new Maven.MavenInstallation[0]))
    mavenDescriptor.save()

    println "Maven configuration completed successfully with version 3.9.2."
} catch (Exception e) {
    println "Error configuring Maven: " + e.message
}
