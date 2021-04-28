pipeline {
    agent {
    kubernetes {
        yaml '''
              apiVersion: v1
              kind: Pod
              spec:
                containers:
                  - name: codeql
                    image: "artifactory.cms.gov/nimbus-jenkins-core-docker-local/centos-codeql:latest"
                    tty: true
                    command: ["tail", "-f", "/dev/null"]
                    imagePullPolicy: Always
             '''
             }
         }
    environment {
      JFROG_CLI_HOME_DIR = "${env.WORKSPACE}/.jfrog"
      JFROG_CLI_OFFER_CONFIG=false
      }

  options { timestamps() }

  stages {

    stage('Git Checkout') {
      steps {
          container('codeql'){
             sh"""
             git clone https://github.com/rmahimalur/codeql-runner.git
             """
          }
        }
      }
    stage('Initializing codeql') {
      steps {
          container('codeql'){
             sh"""
             cd codeql-runner
             codeql-runner-linux init --languages java --config-file .github/codeql/codeql-config.yml --codeql-path /opt/codeql/codeql --repository rmahimalur/codeql-runner --github-url https://github.com --github-auth 3a430525d8fbd7ba31f4fc50994cbe0f779a8182
             """
          }
        }
      }
    stage('monitor and build') {
      steps {
          container('codeql'){
             sh"""
             cd codeql-runner
             chmod +x ${env.WORKSPACE}/codeql-runner/codeql-runner/codeql-env.sh
             . ${env.WORKSPACE}/codeql-runner/codeql-runner/codeql-env.sh
             codeql-runner-linux autobuild --language java
             """
          }
        }
      }
    stage('analyze and upload result to github') {
      steps {
          container('codeql'){
             sh"""
             cd codeql-runner
             codeql-runner-linux analyze --repository rmahimalur/codeql-runner --github-url https://github.com --github-auth 3a430525d8fbd7ba31f4fc50994cbe0f779a8182 --commit 68229a048ce1297a8eac1fc144d43d8b0823559f --ref refs/heads/main
             """
          }
        }
      }
  }
}
