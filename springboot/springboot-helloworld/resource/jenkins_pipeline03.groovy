// failure script
node {
    environment {
        DOCKERHUB_CREDENTIALS=credentials('harbor_credentials')
    }
    stage('Tag') {
        echo 'Tag start'
        if (currentBuild.result == null) {
            echo 'Build success, Tag will be created.'
            sh "git tag -a '${tag_value}' -m 'Build Number ${tag_value}'"
            withCredentials([gitUsernamePassword(credentialsId: 'fernado', gitToolName: 'Default')]) {
                sh 'git push --tags'
            }
        } else {
            echo 'Build failure, Tag will not be created.'
        }
        echo 'Tag end'
    }

    stage('Docker Build') {
        echo 'Docker Build'
        sh '''
        rm -f springboot/${project_name}/docker/*.jar
        cp springboot/${project_name}/target/*.jar springboot/${project_name}/docker/
        # relative path
        docker build -t ${project_name}:${tag_value} springboot/${project_name}/docker/
        '''
    }
    stage('Push image to remote repository') {
        echo 'Push image to remote repository'
        script {
            sh 'image_name=$harbor_url/$harbor_repo/$project_name:${tag_value}'
            sh "echo ${password} | xargs docker login ${harbor_url} -u ${username} --password-stdin"
            sh 'docker tag ${project_name}:${tag_value} ${image_name}'
            sh 'docker push ${image_name}'
        }
        echo 'Push image to remote repository success'
    }
    // if local environment need this
    stage('Pull image from remote repository') {
        echo 'Pull image to remote repository start'

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
        echo $tag_version

        if [[ "$tag_version" != "" ]] && [[ "$tag_version" =~ "${tag_value}" ]]; then
          docker images | grep ${project_name} | awk '{print $3}' | xargs docker rmi -f
        fi
        
        echo $image_name
        docker pull ${image_name}
        '''
        echo 'Pull image to remote repository end'
    }

    stage('Deploy to current') {
        echo 'Deploy to current start'
        sh '''
        image_name=$harbor_url/$harbor_repo/$project_name:${tag_value}
        docker run -d -p ${project_port}:${project_port} --name ${project_name} ${image_name}
        '''
        echo 'Deploy to current done'
    }

    stage('Deploy to remote') {
        echo 'Deploy to remote, not implement yet'
        // notice target server which image it need pull
        // delete the running container in target server
        // delete the image in target server
        // target server pull image from harbor
        // make a running container based on pulled image 
    }
}