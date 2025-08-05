pipeline {
    agent any

    tools {
        maven 'Maven'       // Match the names in Jenkins -> Global Tool Configuration
        jdk 'JDK17'
    }

    environment {
        EMAIL_RECIPIENTS = "vinayprasad.testy@gmail.com"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build and Execute Tests in Docker') {
            steps {
                script {
                    try {
                        bat '''
                        docker run --rm -v "%cd%":/app -w /app maven:3.9.6-eclipse-temurin-17 mvn clean test
                        '''
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build Successful. Sending Email.....'
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "✅ BUILD SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>✅ The build succeeded for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>
                    <p><a href="${env.BUILD_URL}">View Build Logs</a></p>
                """,
                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Build Failed. Sending Email.....'
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "❌ BUILD FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    <p>❌ The build failed for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>
                    <p><a href="${env.BUILD_URL}">View Build Logs</a></p>
                """,
                mimeType: 'text/html'
            )
        }

        always {
            echo '📧 Final Build Status Email.....'
            emailext (
                to: "${EMAIL_RECIPIENTS}",
                subject: "📦 Build Result: ${currentBuild.currentResult}",
                body: """
                    <p>Build finished with status: <b>${currentBuild.currentResult}</b></p>
                    <p>Job: <b>${env.JOB_NAME}</b> | Build #: ${env.BUILD_NUMBER}</p>
                    <p><a href="${env.BUILD_URL}">Open Build</a></p>
                """,
                mimeType: 'text/html'
            )
        }
    }
}