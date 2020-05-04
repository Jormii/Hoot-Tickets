# IMPORTANTE:
# La contraseña root de MySQL es "hoottickets"
# Hay que crear la base de datos "test" después de que termine de ejecutarse el script
# Hay que configurar Haproxy manualmente por ahora

# Java 8
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jre

# MySQL
sudo apt-get install mysql-server

# RabbitMQ
wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -
sudo apt-get update
sudo apt-get install rabbitmq-server

# Haproxy
sudo apt-get update
sudo apt-get -y install haproxy
