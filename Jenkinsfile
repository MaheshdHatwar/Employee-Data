pipeline{
    agent any
    tools{
        maven 'Maven_Home'
    }
    
    stages{
        stage('Git'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MaheshdHatwar/Employee-Management-System']]])
            }
        }
        stage('build the code'){
            steps{
                bat 'mvn clean install package'
                
            }
        }
        stage('Build Docker image'){
            steps{
                bat 'docker build -t m1088994mahesh/employee-management-system .'
            }
        }
        stage('push the code on docker hub'){
            steps{
                withCredentials([usernameColonPassword(credentialsId: 'b0261b65-d927-40dc-89d0-cb31b076183a', variable: 'dockerhub')]) {
                bat 'docker login -u m1088994mahesh -p Nidhitai@123'
                bat 'docker push m1088994mahesh/employee-management-system'
    
}
            }
        }
        
        
    }
}