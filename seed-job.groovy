import jenkins.model.*
import hudson.model.*
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import hudson.plugins.git.BranchSpec

// Get Jenkins instance
def jenkins = Jenkins.getInstance()

// Folder name and job name
def folderName = "example-folder"
def jobName = "angular-pipeline"

// Create the folder if it doesn't exist
def folder = jenkins.getItem(folderName)
if (folder == null) {
    folder = jenkins.createProject(com.cloudbees.hudson.plugins.folder.Folder.class, folderName)
}

// Create the pipeline job inside the folder
def job = folder.getItem(jobName)
if (job == null) {
    job = folder.createProject(WorkflowJob.class, jobName)

    // Define SCM (source control management)
    def scm = new GitSCM(
        [new UserRemoteConfig('https://github.com/AhCap146/angular-tour-of-heroes.git', null, null, null)],
        [new BranchSpec("*/master")], // specify the branch, e.g., "main"
        false,
        [], 
        null, 
        null, 
        [] 
    )
    
    // Set the Jenkinsfile path in the repository
    def definition = new CpsScmFlowDefinition(scm, "Jenkinsfile")
    job.setDefinition(definition)
    job.save()
}

// Save Jenkins state
jenkins.save()
