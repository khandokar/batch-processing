#!/bin/bash
until nc -z -v -w30 mysqldb 3307
do
  echo "Waiting for database connection..."
  # wait for 5 seconds before check again
  sleep 5
done
java -jar /app/batch-processing.jar
exec "$@"