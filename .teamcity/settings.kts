import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.11"

project {

    vcsRoot(Root2)

    subProject(Project2)
}

object Root2 : GitVcsRoot({
    name = "root2"
    url = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    branch = "refs/heads/5"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.SHALLOW_CLONE
})


object Project2 : Project({
    name = "project2"

    buildType(Project2_Build1)
})

object Project2_Build1 : BuildType({
    name = "build1"

    vcs {
        cleanCheckout = true
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo 2"
        }
    }
})
