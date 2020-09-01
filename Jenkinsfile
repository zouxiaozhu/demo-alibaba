#!/usr/bin/env groovy
pipeline {
    // 为整个流水线分配一个执行器
    agent any
    stages {
        stage('mvn version test') {
            steps {
                echo "---------------------------mvn version test---------------------------"
                echo sh(returnStdout: true, script: 'env')
                script {
                    checkDubboResult = false;
                    if (checkDubboResult) {
                        error '校验dubbo包命名不通过，请检查'
                    } else {
                        echo '校验dubbo包命名规范通过'
                    }
                }
            }
            post {
                success { echo "mvn version test" }
            }


        }
    }
}