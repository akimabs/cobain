pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME = 'akimabs/cobain'
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

    //     stage('Login') {
    //         steps {
    //             sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
    //         }
    //     }

    //    stage('Push') {
    //         steps {
    //             script {
    //                 def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
    //                 sh "docker push $IMAGE_NAME:$lastCommitSHA"
    //             }
    //         }
    //     }

       stage('Stop Image at local') {
            steps {
                script {
                    def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    def parentCommitSHA = sh(script: "git rev-parse ${lastCommitSHA}^", returnStdout: true).trim()
                    def imageName = "${IMAGE_NAME}:${parentCommitSHA}"
                    
                    // Check if the container exists before stopping it
                    def existingContainerId = sh(script: "docker ps -aqf name=${imageName}", returnStatus: true, returnStdout: true).trim()
                    
                    if (existingContainerId) {
                        sh "docker stop $imageName"
                        sh "docker rm $existingContainerId"
                        echo "Stopped and removed container $imageName"
                    } else {
                        echo "Container $imageName not found, skipping removal"
                    }
                }
            }
        }


    //    stage('Running Image at local') {
    //         steps {
    //             script {
    //                 def lastCommitSHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
    //                 sh "docker run -d -p 8000:8000 $IMAGE_NAME:$lastCommitSHA"
    //             }
    //         }
    //     }

    }

    post {
        success {
            echo "Success"
            // Archive your build artifacts, send notifications, or perform other post-build actions here
        }
    }
}
