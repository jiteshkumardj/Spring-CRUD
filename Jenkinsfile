pipeline {
    agent any // Use any available Jenkins agent

    environment {
        MAVEN_HOME = tool 'Maven' // Specify Maven installation configured in Jenkins
        APP_NAME = 'Spring-CRUD-App' // Application Name
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from Git repository...'
                checkout scm // Automatically checks out the repository configured in your Multibranch Pipeline job
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building the Spring application using Maven...'
                // Maven command for building the application
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests"
            }
            post {
                success {
                    echo "Build successful! Artifacts are created in the target directory."
                }
                failure {
                    echo "Build failed! Check the logs for details."
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs() // Clean the workspace after execution
        }
        success {
            echo "Pipeline executed successfully for ${APP_NAME}!"
        }
        failure {
            echo "Pipeline failed! Investigate issues and retry."
        }
    }
}
