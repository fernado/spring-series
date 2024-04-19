```groovy
node {
    def mvnHome
    stage('Preparation') { // for display purposes
        echo 'Checkout'
        checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'fernado', url: 'https://github.com/fernado/spring-series.git']])
        mvnHome = tool 'maven396'
    }
    stage('Analyze') {
        echo 'Sonaqube'
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" clean verify sonar:sonar -Dsonar.projectKey=spring-series -Dsonar.projectName=spring-series -Dsonar.host.url=http://192.168.56.102:9000 -Dsonar.token=sqp_d7c7ceaf4a439b75f96e195b6888774a04f17079'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }
    stage('Build') {
        echo 'Build'
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }
    stage('Test Results') {
        echo 'Test Results'
        sh 'make check || true' 
        junit '**/target/surefire-reports/TEST-*.xml'
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }
    stage('Deploy') {
        echo 'Deploy'
        // when {
        //   expression {
        //     currentBuild.result == null || currentBuild.result == 'SUCCESS' 
        //   }
        // }
        // steps {
        //     sh 'make publish'
        // }
    }
}


```