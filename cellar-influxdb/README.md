Run influxdb locally

```bash
$ docker run --name influxdb -p 8086:8086 quay.io/influxdb/influxdb:2.0.0-rc
or 
$ docker start influxdb 
```

cellar / robottle

for the CLI
```bash
$ docker exec -it influxdb /bin/bash
```

https://app-f68769bb-2fc0-4537-aae3-e3441ce75c81.cleverapps.io

```config
CC_DOCKER_EXPOSED_HTTP_PORT 8086
CC_DOCKERFILE cellar-influxdb/Dockerfile
PORT 8080
```