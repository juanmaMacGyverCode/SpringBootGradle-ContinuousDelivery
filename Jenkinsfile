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
        stage ("Docker build") {
            steps {
                sh "docker build -t juanmamacgyvercode/calculator-complete-cicd ."
            }
        }
        stage ("Probar si funciona Docker") {
            steps {
                sh "docker version"
            }
        }
        stage ("Docker login") {
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'DOCKER-CREDENTIALS',
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh 'echo "$PASSWORD" | docker login --username "$USERNAME" --password-stdin'
                }
            }
        }
        stage ("Docker push") {
            steps {
                sh "docker push juanmamacgyvercode/calculator-complete-cicd"
            }
        }
    }
    post {
        always {
             sh "docker logout juanmamacgyvercode"
        }
    }
}