pipeline {
    agent any
    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                        reportDir: 'build/reports/jacoco/test/html',
                        reportFiles: 'index.html',
                        reportName: 'JacocoReport'
                    ])
                    sh "./gradlew jacocoTestCoverageVerification"
                }
            }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube-CICD-Completo') {
                     sh './gradlew sonarqube'
                }
            }
        }
        stage ("Package") {
            steps {
        	    sh "./gradlew build"
        	}
        }
        stage ("Probar si funciona Docker") {
            steps {
                sh "docker version"
            }
        }
    }
    post {
        always {
             sh "docker logout juanmamacgyvercode"
        }
    }
}