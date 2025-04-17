pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                    git branch: 'main', url: 'https://github.com/sharonjose679/technician-app.git'
            }
        }
 stage('Start Emulator & Appium') {
            steps {
                bat 'start_galaxy_tab.bat'
            }
        }

        stage('Build & Run Tests') {
            steps {
              bat 'mvn clean test -DsuiteXmlFile=testng.xml -DdeviceName=Samsung_Galaxy_Tab_Active_3'
            }
        }
    }
}