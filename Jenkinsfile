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
                to: "vinayprasad.testy@gmail.com",
                subject: "BUILD SUCCESS: Job ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "<p>✅ The build succeeded for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>"


                mimeType: 'text/html'
            )
        }

        failure {
            echo '❌ Build Failed. Sending Email and Retrying...'

            emailext (
                to: "vinayprasad.testy@gmail.com",
                subject: "BUILD FAILURE: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
                body: "<p>❌ The build failed for <b>${env.JOB_NAME}</b> #${env.BUILD_NUMBER}.</p>",

                mimeType: 'text/html'
            )
        }

        always {
            emailext (
                to: "vinayprasad.testy@gmail.com",
                subject: "Build Result: ${currentBuild.currentResult}",
                body: """<p>Build finished with status: <b>${currentBuild.currentResult}</b></p>",


            )
        }
    }
}