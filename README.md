# Candidate_Coding_Challenge

## Organisation of the code
I've created two folders:
* test: containing the code of the Java microservice I want to add.
* local-dev-env: containing the files for deployment with docker compose.

## Steps to run the app
1. change directory into "test" and run the command "mvn clean package" to build the war file to deploy on docker.
2. go to *server/target* and copy the file *server-1.0-SNAPSHOT.war* into *local-dev-env/wildfly/target* folder and rename the file to *test.war*
3. cd into *local-dev-env*
4. run the command "docker-compose build" to build images
5. run the command "docker-compose up -d" to start the services
6. You can test.