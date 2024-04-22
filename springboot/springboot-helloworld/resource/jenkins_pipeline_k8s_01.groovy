pipeline {
    agent any

    environment {
        project_name = "springboot-helloworld"
        project_port = "10090"
        harbor_user = "tester"
        harbor_passwd = "Tester123456"
        harbor_url = "192.168.56.102:5100"
        harbor_repo = "p_pub"
    }

    stages {

        stage('Preparation') { // for display purposes
            steps {
                echo 'Checkout'
                checkout scmGit(branches: [[name: '*/${branch_name}']], extensions: [], userRemoteConfigs: [[credentialsId: 'fernado', url: 'https://github.com/fernado/spring-series.git']])
            }
        }

        stage('Analyze') {
            steps {
                echo 'Sonaqube'
                sh '"/home/apache-maven-3.9.6/bin/mvn" clean verify sonar:sonar -Dsonar.projectKey=spring-series -Dsonar.projectName=spring-series -Dsonar.host.url=http://192.168.56.102:9000 -Dsonar.token=sqp_d7c7ceaf4a439b75f96e195b6888774a04f17079'
            }

        }

        stage('Build') {
            steps {
                echo 'Build'
                sh '"/home/apache-maven-3.9.6/bin/mvn" -Dmaven.test.skip=true -Dmaven.test.failure.ignore clean package'
            }
        }

        stage('Test Results') {
            steps {
                echo 'Test Results'
                sh 'make check || true'
                junit 'springboot/${project_name}/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: 'springboot/${project_name}/target/*.jar', fingerprint: true
            }
        }

        stage('Tag') {
            steps {
                echo 'Tag'
                echo 'Build success, Tag will be created.'
                sh "git tag -a '${tag_value}' -m 'Build Number ${tag_value}'"
                withCredentials([gitUsernamePassword(credentialsId: 'fernado', gitToolName: 'Default')]) {
                    sh 'git push --tags'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Docker Build'
                sh '''
                rm -f springboot/${project_name}/docker/*.jar
                cp springboot/${project_name}/target/*.jar springboot/${project_name}/docker/
                # relative path
                docker build -t ${project_name}:${tag_value} springboot/${project_name}/docker/
                '''
            }
        }

        stage('Push image to remote repository') {
            steps {
                echo 'Push image to remote repository'
                sh '''
                image_name=$harbor_url/$harbor_repo/$project_name:${tag_value}
                docker login -u $harbor_user -p $harbor_passwd ${harbor_url}
                docker tag ${project_name}:${tag_value} ${image_name}
                docker push ${image_name}
                '''
                echo 'Push image to remote repository success'
            }
        }

        stage('Push image to remote k8smaster') {
            steps {
                sshPublisher(publishers: [sshPublisherDesc(configName: 'k8smaster', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'springboot/spring-series/docker/springboot-helloworld.yaml')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

    }
}