pipeline {
    agent any

    tools {
        maven 'Maven'       // Must match Global Tool Config
        jdk 'JDK17'         // Ensure this label matches what's in Jenkins > Global Tools
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

        stage('Build and Execute Tests') {
            steps {
                script {
                    try {
                        bat 'mvn clean test'
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
            echo '✅ Build Successful. Sending Email...'
            emailext (
                subject: "SUCCESS: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>✅ The build succeeded for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>
                         <p>Check it here: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                to: "vinayprasad.testy@gmail.com",
                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Build Failed. Sending Email and Retrying...'

            emailext (
                subject: "FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: """<p>❌ The build failed for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>
                         <p>Check it here: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                to: "vinayprasad.testy@gmail.com",
                mimeType: 'text/html'
            )
        }

        always {
            emailext (
                to: "vinayprasad.testy@gmail.com",
                subject: "Build Result: ${currentBuild.currentResult}",
                body: """<p>Build finished with status: <b>${currentBuild.currentResult}</b></p>
                         <p>View it: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>""",
                attachLog: true
            )
        }
    }
}