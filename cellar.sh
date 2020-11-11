#!/bin/sh
SERVICE_NAME=cellar
PATH_TO_JAR=/home/pi/projects/cellar-dist/cellar-app.jar
PID_PATH_NAME=/tmp/cellar.pid
export MONITORING_ENABLED=true

case $1 in
start)
       echo "Starting $SERVICE_NAME ..."
  if [ ! -f $PID_PATH_NAME ]; then
       /usr/bin/java -jar -Dmonitoring.enabled=true $PATH_TO_JAR
       echo "$SERVICE_NAME started ..."
  else
       echo "$SERVICE_NAME is already running ..."
  fi
;;
stop)
  if [ -f $PID_PATH_NAME ]; then
         PID=$(cat $PID_PATH_NAME);
         echo "$SERVICE_NAME stopping ..."
         kill $PID;
         echo "$SERVICE_NAME stopped ..."
         rm $PID_PATH_NAME
  else
         echo "$SERVICE_NAME is not running ..."
  fi
;;
restart)
  if [ -f $PID_PATH_NAME ]; then
      PID=$(cat $PID_PATH_NAME);
      echo "$SERVICE_NAME stopping ...";
      kill $PID;
      echo "$SERVICE_NAME stopped ...";
      rm $PID_PATH_NAME
      echo "$SERVICE_NAME starting ..."
      /usr/bin/java -jar $PATH_TO_JAR
      echo "$SERVICE_NAME started ..."
  else
      echo "$SERVICE_NAME is not running ..."
     fi     ;;
 esac