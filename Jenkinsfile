pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME = 'akimabs/cobain'
        DEPLOYMENT_FILE = 'k8s/deployment.yml'
        HPA_FILE = 'k8s/hpa.yml'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Apps') {
            steps {
                script{
                    sh "./mvnw clean install -DskipTests=true"
                }
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

        stage('Describe pod') {
            steps{
                script{
                    sh "kubectl get all -n ciam"
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    def parentCommitSHA = sh(script: "git rev-parse ${lastCommitSHA}^", returnStdout: true).trim()
                    def imageName = "${IMAGE_NAME}:${parentCommitSHA}"
                    def shSetImage = sh(script: sh "kubectl set image deployment/apps-cobain apps-cobain=$imageName -n ciam", returnStdout: true).trim()
                    
                    sh 'kubectl apply -f $DEPLOYMENT_FILE -n ciam'
                    sh 'kubectl apply -f $HPA_FILE -n ciam'
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
