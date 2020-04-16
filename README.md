# java-k8s-cicd

* Fork repository
* Create Jenkins pipeline
* build mvn Project with given pom.xml
* Create Docker container with Jenkins pipeline(use the java artifact that you created)
* Push Docker to Nexus Docker Repositry with Jenkins
* Create K8s Deploymentfile with node port
* deploy the docker container that you build with Version # in K8s.

Notes: Container port : 8080

with http://IPADD:NODEPORT you should be able to access the java application.
