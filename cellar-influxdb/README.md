# Run influxdb locally

```bash
$ docker run --name influxdb -p 8086:8086 quay.io/influxdb/influxdb:v2.0.1
or 
$ docker start influxdb 
```

for the CLI
```bash
$ docker exec -it influxdb /bin/bash
```

# Currently running 'PROD' on a clever cloud app.
 
```config
CC_DOCKER_EXPOSED_HTTP_PORT 8086
CC_DOCKERFILE cellar-influxdb/Dockerfile
PORT 8080
```

to deploy a new version : 

```
$ git remote -u clever master # you need to have the remote setup first
```

## Backup and Restore

WIP. See `backup.sh` and `restore.sh`