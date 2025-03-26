pipeline {
    agent any // Use any available Jenkins agent

    environment {
        MAVEN_HOME = tool 'Maven' // Specify Maven installation path in Jenkins
        APP_NAME = 'Spring-CRUD-App'   // Application name
        ARTIFACT_NAME = 'spring-crud.war' // WAR file name for deployment
    }

    options {
        buildDiscarder(logRotator(daysToKeepStr: '30', numToKeepStr: '10')) // Keep logs for 30 days or 10 builds
        timeout(time: 60, unit: 'MINUTES') // Set pipeline timeout to 1 hour
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from Git repository...'
                checkout scm // Automatically checkout code linked to the Multibranch Pipeline job
            }
        }

        stage('Prepare Environment') {
            steps {
                echo 'Setting up environment...'
                // Ensure Java and Maven are installed
                sh 'java -version'
                sh '${MAVEN_HOME}/bin/mvn -v' // Print Maven version
            }
        }

        stage('Build Application') {
            steps {
                echo 'Building the Spring application using Maven...'
                sh "${MAVEN_HOME}/bin/mvn clean package -DskipTests" // Build application and generate WAR file
            }
            post {
                success {
                    echo "Build successful! The WAR file is ready."
                }
                failure {
                    echo "Build failed! Check the logs for details."
                }
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
                sh "${MAVEN_HOME}/bin/mvn test" // Run unit tests
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml' // Publish test reports from Maven's surefire plugin
                }
            }
        }

        stage('Package Artifact') {
            steps {
                echo "Packaging build output..."
                archiveArtifacts artifacts: "target/${ARTIFACT_NAME}", allowEmptyArchive: false // Archive the WAR file
            }
        }

        stage('Deploy Application') {
            steps {
                echo "Deploying ${APP_NAME} to staging environment..."
                sh """
                cp target/${ARTIFACT_NAME} /var/lib/tomcat/webapps/  // Example deployment command
                """
            }
            post {
                success {
                    echo "Deployment successful! Application is available in staging."
                }
                failure {
                    echo "Deployment failed! Please investigate the logs."
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
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed! Investigate and retry.'
        }
    }
}
