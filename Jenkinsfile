node('master') {
  // stage 'Build and Test'
  def mvnHome = tool 'maven3.2.5'
  env.JAVA_HOME = tool 'jdk6'
  env.ANT_HOME = tool 'ant1.9.8'
  env.GRAILS_HOME = tool 'grails2.2.4'
  env.GRADLE_HOME = tool 'gradle3.3'

  env.PATH = "${env.ANT_HOME}/bin:${env.JAVA_HOME}/jre/bin:${env.GRAILS_HOME}/bin:${env.GRADLE_HOME}/bin:${mvnHome}/bin:${env.JENKINS_HOME}/bin:./:${env.PATH}"
  checkout scm
  
  stage('Build PersonRegistry') {
    sh 'grails war'
  }
}
