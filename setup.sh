#!/bin/bash

APP_IMAGE_NAME=mobileapp

echo "Χτίζουμε Docker image"
docker build -t $APP_IMAGE_NAME .

echo "Δημιουργία volume για MySQL δεδομένα"
docker volume create mysql_data

echo "Τέλος setup."
