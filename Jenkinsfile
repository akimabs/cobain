pipeline {
    agent any

    environment {
        // Define environment variables here
        DOCKER_REGISTRY = 'docker.io'  // Docker Hub registry
        DOCKERHUB_USERNAME = credentials('akimabs')
        DOCKERHUB_PASSWORD = credentials('Akskt2004')
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
                    // Build the Docker image with the specified name and tag
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    echo "Last Commit SHA: ${lastCommitSHA}"
                    def dockerImage = docker.build("${IMAGE_NAME}:${lastCommitSHA}")
                }
            }
        }

        stage('Publish to Docker Hub') {
            steps {
                script {
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    echo "Last Commit SHA: ${lastCommitSHA}"
                    // Log in to Docker Hub with credentials
                    docker.withRegistry("https://${DOCKER_REGISTRY}", DOCKERHUB_USERNAME, DOCKERHUB_PASSWORD) {
                        // Push the Docker image with the specified name and tag
                        def dockerImage = docker.image("${IMAGE_NAME}:${lastCommitSHA}")
                        dockerImage.push()
                    }
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
