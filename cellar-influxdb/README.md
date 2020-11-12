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

