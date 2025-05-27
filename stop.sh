#!/bin/bash

APP_CONTAINER_NAME=mobileapp_container
DB_CONTAINER_NAME=mysql_container
NETWORK_NAME=mobile_network

echo "Σταμάτημα εφαρμογής και βάσης"
docker stop $APP_CONTAINER_NAME $DB_CONTAINER_NAME

echo "🗑Διαγραφή containers"
docker rm $APP_CONTAINER_NAME $DB_CONTAINER_NAME

echo "Διαγραφή δικτύου"
docker network rm $NETWORK_NAME

# Δεν διαγράφουμε volumes για να κρατήσουμε τα δεδομένα της MySQL
echo "Η υποδομή καθαρίστηκε επιτυχώς"
