pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        IMAGE_NAME = 'akimabs/cobain'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    echo "Last Commit SHA: ${lastCommitSHA}"
                    sh "docker build -t $IMAGE_NAME:$lastCommitSHA ."
                }
            }
        }

        stage('Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Push') {
            steps {
                script {
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    sh 'docker push $IMAGE_NAME:$lastCommitSHA'
                }
            }
        }
    }

    post {
        success {
            echo "Success"
            // Archive your build artifacts, send notifications, or perform other post-build actions here
        }
    }
}
