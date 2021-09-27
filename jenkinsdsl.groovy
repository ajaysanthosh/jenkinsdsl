job('simple-maven-app') {
    scm {
        git { 
            branch('master')
            remote {
                credentials('d8d4ca02-842b-41e9-a898-31233eab8e35')
                github('ajaysanthosh/simple-java-maven-app', protocol = 'https', host ='github.com')
                }
        }
    }
    steps {
        shell{
            command('echo "WORKSPACE: ${WORKSPACE}";echo "Jenkins Job Name: ${JOB_NAME}"')
        }
        maven{
            goals('clean package')
            mavenInstallation('Maven 3.8')
            properties(skipTests: true)
        } 
        maven{
            goals('test')
            mavenInstallation('Maven 3.8')
        }
     }
     publishers{
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/**/*.xml')

     }
}

pipelineJob('simple-maven-app-my-pipeline') {
    description ('This job is a pipelinejob')

    definition{
        cpsScm{
                scm {
                    git {
                        branch('master')
                        remote {
                            credentials('d8d4ca02-842b-41e9-a898-31233eab8e35')
                            github('ajaysanthosh/simple-java-maven-app', protocol = 'https', host ='github.com')
                        }
                    }
                }
                scriptPath('Jenkinsfile')
                lightweight(lightweight = true)
            }

    }
}
