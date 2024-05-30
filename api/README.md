# RMIT api

## Requirements
* JDK 21
* Maven 3

## Other requirements
* Postgres 16
* Elastic search 8.13.4

## Running the application tests
* Run the tests: `mvn verify`
### NOTE! 
If you're planning to run tests inside the docker file use:
````
docker run --rm -p 2375:2375 -v //var/run/docker.sock://var/run/docker.sock alpine/socat tcp-listen:2375,reuseaddr,fork unix-connect:/var/run/docker.sock
````

## Running the application locally
* Clone the repository: `git clone https://github.com/rmit/api.git`
* Navigate to the project directory: `cd api`
* Build the project: `mvn clean package`
* Start Postgres
  * spring.datasource.url=jdbc:postgresql://localhost:5432/app-db
  * spring.datasource.username=app-db
  * spring.datasource.password=app-db
* Elastic search
  * spring.jpa.properties.hibernate.search.backend.hosts=localhost:9200
  * spring.jpa.properties.hibernate.search.backend.protocol=http
* Run the application: `mvn spring-boot:run`

## Running the application in a container
* Docker
    * Build the Docker image: `docker build -t rmit-api .`
    * Run the Docker container: `docker run -p 8080:8080 rmit-api`