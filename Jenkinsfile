pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/sharonjose679/technician-app.git'
            }
        }

        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}