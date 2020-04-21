sudo service haproxy start
java -jar ./Site_HTTP.jar --server.port=8080 &
java -jar ./Site_HTTP.jar --server.port=8081 &
java -jar ./Service.jar &
