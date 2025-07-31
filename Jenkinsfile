pipeline {
    agent any

    tools {
        maven 'Maven'   // Make sure these are configured in Jenkins
        jdk 'JDK17'     // Rename if needed in Global Tool Configuration
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
                bat 'mvn clean test'
            }
        }



    }

    post {
        success {
            echo '✅ Build Successful. Sending Email...'
            emailext (
                subject: "SUCCESS",
                body: """<p>✅ The build succeeded!</p>"""

                to: "${EMAIL_RECIPIENTS}",
                mimeType: 'text/html; charset=UTF-8',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
        }

        failure {
            echo '❌ Build Failed. Sending Email and Retrying...'
            emailext (
                subject: "FAILURE",
                body: """<p>❌ The build failed!</p>"""

                to: "${EMAIL_RECIPIENTS}",
                mimeType: 'text/html; charset=UTF-8',
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
            retry(2) {
                bat 'mvn clean test'
            }
        }

        always {
            emailext (
                        to: "${EMAIL_RECIPIENTS}",
                        subject: "Test",
                        body: "Build finished.",
                        attachLog: true,
                        recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                    )
        }
    }
}