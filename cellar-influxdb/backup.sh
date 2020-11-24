#!/bin/bash

BACKUP_NAME=$(date +'%d-%m-%Y-%H')
echo "Creating $BACKUP_NAME.zip"

cd /home/julien/backups
influx backup $BACKUP_NAME --token $CELLAR_ADMIN_TOKEN
zip -r $BACKUP_NAME.zip $BACKUP_NAME
rm -r $BACKUP_NAME