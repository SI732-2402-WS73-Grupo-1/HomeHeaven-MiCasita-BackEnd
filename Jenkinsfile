pipeline {
    agent any
    tools {
        maven 'MAVEN_3_6_3'
        jdk 'JDK_21'
    }

    stages {
        stage ('Compile Stage 2024-02') {
            steps {
                withMaven(maven : 'MAVEN_3_6_3') {
                    bat 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage 2024-02') {
            steps {
                withMaven(maven : 'MAVEN_3_6_3') {
                    bat 'mvn test'
                }
            }
        }
    }
}
