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
        stage ("Deploy to staging") {
            steps {
                sh "docker run -d --rm -p 8765:8080 --name calculatorStaging juanmamacgyvercode/calculator-complete-cicd"
            }
        }
        stage ("Acceptance test") {
             steps {
                 sleep 60
                 sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
             }
        }
    }
    post {
        always {
            sh "docker stop calculatorStaging"
            sh "docker logout juanmamacgyvercode"
        }
    }
}