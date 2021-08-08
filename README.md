# benchm_priv
1. Run docker:
sudo docker run -d -p 8181:80 khoshaba/ubuntu_apache_html

2. For run cload modul:
./mvnw spring-boot:run
curl localhost:8080/cload
curl localhost:8080/cload?address=lala
curl localhost:8080/cload?address=lala\&port=2020
curl localhost:8080/cload?address=lala\&port=2020\&series=3
curl localhost:8080/cload?address=lala\&port=2020\&series=3\&requests=5
curl localhost:8080/cload?address=lala\&port=2020\&series=3\&requests=5\&dt=17
curl localhost:8080/cload?address=lala\&port=2020\&series=3\&requests=5\&dt=17\&fileName=data22.txt


For useful links:
-----------------------------------
https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
https://attacomsian.com/blog/read-write-csv-files-core-java
https://www.geeksforgeeks.org/reading-csv-file-java-using-opencsv/?ref=rp

ToDo:
Add PeriodOfTime
Add fileName and PeriodOfTime in parameters

