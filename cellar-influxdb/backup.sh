#!/bin/sh

influx backup $BACKUP_NAME --token $CELLAR_ADMIN_TOKEN --host $CELLAR_HOST
zip -r $BACKUP_NAME.zip $BACKUP_NAME
az storage blob upload \
    --account-name "cellarbackupstorage" \
    --container-name "cellar-backups" \
    --name $BACKUP_NAME.zip \
    --file $BACKUP_NAME.zip \
    --auth-mode login