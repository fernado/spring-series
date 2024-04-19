pipeline {
// need parameters
// tag_value:v1.1.26 -- increment as deploy each time
// branch_name:main -- branch
    agent any

    environment {
        project_name = "springboot-helloworld"
        project_port = "10090"
        harbor_user = "tester"
        harbor_passwd = "Tester123456"
        harbor_url = "192.168.56.102:5100"
        harbor_repo = "p_pub"
    }

    stages{

        stage('Preparation') { // for display purposes
            steps {
                eecho 'Checkout'
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
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
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

        // if local environment need this
        stage('Pull image from remote repository') {
            steps {
                echo 'Pull image to remote repository start'
                script {
                    sh '''
                    image_name=$harbor_url/$harbor_repo/$project_name:${tag_value}
                    containerId=`docker ps -a | grep ${project_name} | awk '{print $1}'`
                    echo $containerId
                    if [ "$containerId" != "" ]; then
                      docker stop $containerId
                      docker rm $containerId
                    else
                      echo 'No' $project_name 'container is running'
                    fi 
                    tag_version=`docker images | grep ${project_name} | awk '{print $2}'`
                    echo tag_version = ${tag_version}
                    echo tag_value = ${tag_value}
            
                    if [[ "$tag_version" != "" && "$tag_version" =~ "${tag_value}" ]]; then
                      docker images | grep ${project_name} | awk '{print $3}' | xargs docker rmi -f
                    fi
                    
                    docker login -u $harbor_user -p $harbor_passwd ${harbor_url}
                    echo $image_name
                    docker pull ${image_name}
                    '''
                }
            }

        }

        stage('Deploy to current') {
            steps {
                echo 'Deploy to current start'
                sh '''
                image_name=$harbor_url/$harbor_repo/$project_name:${tag_value}
                docker run -d -p ${project_port}:${project_port} --name ${project_name} ${image_name}
                '''
                echo 'Deploy to current done'
            }
        }

        stage('Deploy to remote') {
            steps {
                echo 'Deploy to remote, not implement yet'
                // notice target server which image it need pull
                // delete the running container in target server
                // delete the image in target server
                // target server pull image from harbor
                // make a running container based on pulled image
            }
        }
    }

}