#!/usr/bin/env groovy
pipeline {
    // 为整个流水线分配一个执行器
    agent any
    stages {
        stage('mvn version test') {
            steps {
                sh 'mvn version'
            }
            post {
                success { echo "mvn version test" }
            }
        }
    }
}