pipeline {
    agent any

    tools {
        maven "MAVEN_HOME" // Configure Maven as per your setup
    }

    stages {
        stage('Checkout') {
            steps {
                // Clone the repository's master branch
                git branch: 'master', url: 'https://github.com/PavanJessy/Capstone_Project_Megento_Luma.git'
            }
        }
        stage('Build and Test') {
            steps {
                // Run Maven to build the project and execute TestNG tests
                bat "mvn clean package -DsuiteXmlFile=testng.xml"
            }
        }
    }
    post {
        success {
            // Archive test reports and artifacts
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts 'target/*.jar'
        }
        failure {
            echo 'Build or Tests Failed!'
        }
    }
}
