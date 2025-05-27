#!/bin/bash

APP_IMAGE_NAME=mobileapp
APP_CONTAINER_NAME=mobileapp_container
DB_CONTAINER_NAME=mysql_container
NETWORK_NAME=mobile_network
MYSQL_ROOT_PASSWORD=secret
MYSQL_DATABASE=mobile_db
MYSQL_VOLUME=mysql_data

echo "Δημιουργία δικτύου"
docker network create $NETWORK_NAME

echo "Εκκίνηση MySQL"
docker run -d \
  --name $DB_CONTAINER_NAME \
  --network $NETWORK_NAME \
  -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=$MYSQL_DATABASE \
  -v $MYSQL_VOLUME:/var/lib/mysql \
  mysql:8

echo "Αναμονή για MySQL να ξεκινήσει"
until docker exec $DB_CONTAINER_NAME mysqladmin ping -h"localhost" --silent; do
  printf "."
  sleep 2
done
echo "MySQL είναι έτοιμη"

echo "Εκκίνηση εφαρμογής"
docker run -d \
  --name $APP_CONTAINER_NAME \
  --network $NETWORK_NAME \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql_container:3306/mobile_db \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=$MYSQL_ROOT_PASSWORD \
  -p 8080:8080 \
  $APP_IMAGE_NAME

echo "Η εφαρμογή τρέχει στο http://localhost:8080"
