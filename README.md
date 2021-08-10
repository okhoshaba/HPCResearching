# benchmark

1. Run docker:
sudo docker run -d -p 8181:80 khoshaba/ubuntu_apache_html

2. For run cload module:
./mvnw spring-boot:run
curl localhost:8080/cload
curl localhost:8080/cload?address=localhost
curl localhost:8080/cload?address=localhost\&port=8181
curl localhost:8080/cload?address=localhost\&port=8181\&series=3
curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5
curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5\&dt=17
curl localhost:8080/cload?address=localhost\&port=8181\&series=3\&requests=5\&dt=17\&fileName=data22.txt

3. For iqr data processing:
curl localhost:8080/iqr
curl localhost:8080/iqr?fileName=data22

For useful links:
-----------------------------------
https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
https://attacomsian.com/blog/read-write-csv-files-core-java
https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/?ref=rp

ToDo:
Coreect (edit) code in Iqr class
Define mediana, deviation.


