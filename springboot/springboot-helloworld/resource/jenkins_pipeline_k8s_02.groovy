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


        stage('Push image to remote k8smaster') {
            steps {
                sshPublisher(
                        publishers: [
                                sshPublisherDesc(
                                    configName: 'k8smaster',
                                    transfers: [
                                            sshTransfer(
                                                    cleanRemote: false,
                                                    excludes: '',
                                                    execCommand: '',
                                                    execTimeout: 120000,
                                                    flatten: false,
                                                    makeEmptyDirs: false,
                                                    noDefaultExcludes: false,
                                                    patternSeparator: '[, ]+',
                                                    remoteDirectory: '',
                                                    remoteDirectorySDF: false,
                                                    removePrefix: 'springboot/springboot-helloworld/docker',
                                                    sourceFiles: 'springboot/springboot-helloworld/docker/springboot-helloworld.yaml'
                                            )],
                                    usePromotionTimestamp: false,
                                    useWorkspaceInPromotion: false,
                                    verbose: false
                                )
                        ]
                )
            }
        }

        stage('Execute K8s kubectl remotely') {
            steps {
                sh '''
                ssh tester@192.168.56.102 kubectl apply -f /home/tester/app/deploy/springboot-helloworld.yaml
                '''
            }
        }

    }
}