pipeline {
    agent any
    environment {
        CI = 'true'
    }
    stages {
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(credentialsId: 'sonar-jenkins', installationName: 'my-sonar') { // You can override the credential to be used
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
                }
                // timeout(time: 1, unit: 'MINUTES') {
                //     waitForQualityGate abortPipeline: false
                // }
            }
        }
        stage('Build image') { 
            steps {
                script {
                    echo "Build image with tag: ${env.BUILD_ID}"
                    dockerImage = docker.build("172.28.128.3:30700/java-k8s-cicd:${env.BUILD_ID}")
                }
            }
        }
        stage('Push image to registry') { 
            steps {
                script {
                    docker.withRegistry('http://172.28.128.3:30700', 'nexus-user-and-password') {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }
        stage('Deploy to K8s cluster') { 
            steps {
                script {
                    // withCredentials([kubeconfigFile(credentialsId: 'k8s-config', variable: 'KUBECONFIG')]) {
                    //     sh '''
                    //         kubectl version
                    //         sed -i 's/latest/'"${BUILD_ID}"'/g' java-k8s-cicd-k82-deployment.yaml
                    //         kubectl --kubeconfig=${KUBECONFIG} apply -f java-k8s-cicd-k82-deployment.yaml
                    //     '''
                    // }
                    withCredentials([sshUserPrivateKey(credentialsId: 'vagrant-ssh', keyFileVariable: 'SSHKEY')]) {
                      sh '''
                        sed -i 's/latest/'"${BUILD_ID}"'/g' java-k8s-cicd-deployment.yaml
                        sed -i 's/REGIPADD/172.28.128.3:30700/g' java-k8s-cicd-deployment.yaml

                        cat nodejs-k8s-cicd-deployment.yaml

                        scp -o StrictHostKeyChecking=no -i ${SSHKEY} java-k8s-cicd-deployment.yaml vagrant@10.0.2.15:/home/vagrant/
                        ssh -i ${SSHKEY} vagrant@10.0.2.15 kubectl apply -f java-k8s-cicd-deployment.yaml
                      '''
                    }
                    
                }
            }
        }
        stage('Remove unused docker image') { 
            steps {
                sh "docker image rm 172.28.128.3:30700/java-k8s-cicd:${env.BUILD_ID}"
            }
        }
    }
}