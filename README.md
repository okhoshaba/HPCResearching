# HPCResearching

HPCResearching - A Software Environment for High-Performance Computing research and development.

HPC Researching is a modular and service-oriented software environment for studying the load impact of requests on services in computer systems.

The main purpose of the software environment is to determine quantitative and qualitative assessments of the load impacts on services in computer systems.

License
-------

HPCResearching source code is licensed under the GPL v3.0. See `LICENSE` for details. 

Build and Develop Requirements    
------------------    
To build and develop a software environment, you need requirements:

- Maven;

- Docker.

 
Getting HPCResearching
--------------    
 
Getting HPCResearching is as easy as:
 
    git clone git@github.com:okhoshaba/HPCResearching.git

Each module of the software environment runs in a container. For example, to study a web service as an object of research, you need to run the command:

sudo docker run -d -p 8181:80 khoshaba/ubuntu_apache_html

For build cload module:

in cload catalog ./mvnw clean package 

For run cload module:

in cload catalog java -jar target/cload-0.0.3-SNAPSHOT.jar

Or for build and run cload module:

./mvnw spring-boot:run

In another terminal, you can run:

curl localhost:8080/cload

curl localhost:8080/cload?address=localhost

curl localhost:8080/cload?address=localhost\&port=8181

curl localhost:8080/cload?address=localhost\&port=8181\&series=3

curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5

curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5\&dt=17

curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5\&dt=17\&fileName=data22.txt

For iqr data processing:

curl localhost:8080/iqr

curl localhost:8080/iqr?fileName=data22

For useful links:
-----------------------------------
...



