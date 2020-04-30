sudo docker-compose build
sudo docker-compose up --scale site=2 --scale service=2 --remove-orphans
