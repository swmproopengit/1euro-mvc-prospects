pipeline {
  agent { label 'linux' }
  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }
  environment {
    HEROKU_API_KEY = credentials('devsahamerlin-heroku-api-key')
  }
  parameters {
    string(name: 'APP_NAME', defaultValue: '', description: 'What is the Heroku app name?')
  }
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t devsahamerlin/1euro-mvc-prospects:latest .'
      }
    }
    stage('Login') {
      steps {
        sh 'echo $HEROKU_API_KEY | docker login --username=_ --password-stdin registry.heroku.com'
      }
    }
    stage('Push to Heroku registry') {
      steps {
        sh '''
          docker tag devsahamerlin/1euro-mvc-prospects:latest registry.heroku.com/$APP_NAME/web
          docker push registry.heroku.com/$APP_NAME/web
        '''
      }
    }
    stage('Release the image') {
      steps {
        sh '''
          heroku container:release web --app=$APP_NAME
        '''
      }
    }
  }
  post {
    always {
      sh 'docker logout'
    }
  }
}