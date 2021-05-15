# Springboot demo app with graylog in docker

This is demo springboot app with graylog.
Graylog is configured via `GELF TCP Async`.
For logging we use logback.
At the moment environment is settled up using `docker-compose` and there 4 endpoints to test:
- `GET /hello`. This one generates info and debug logs
- `GET /messages`
- `POST /messages`
- `DELETE /messages/{id}`

## Challenge 1:
Deploy all in kubernetes(`microk8s`). 
   
### Success Criteria: 
Generate logs in app. See logs appears in graylog.  

### Delivery:
- PR on branch `challenge1`
- Readme with info how to setup and deploy

## Challenge 2:
Upgrade containers to the latest mongo, graylog and elasticsearch.

### Success Criteria:
You can bring env `up with docker-compose` with upgraded containers + `Challenge 1 Sucess Criteria`

### Delivery: 
- PR on branch `challenge2`
- Readme with info how to setup and deploy

## Challenge 3:
Deploy mongodb in kubernetes cluster using MongoDB Community Kubernetes Operator and separate read and write over mongo nodes
Add logging about reading and writing operations to mongodb.

### Success Criteria:
- All deployed on kubernetes(`microk8s`): demoapp, mongodb, graylog, elasticsearch
- mongodb deployed in cluster with 3 nodes using MongoDB Community Kubernetes Operator
- Write to mongo over master node
- Read from replica nodes
- Both read and write operations to mongodb are logged (via logger) and visible in app console/output
- Readme with info how to setup and deploy

### Delivery:
- PR on branch `challenge3`.
- Readme with info how to setup and deploy

## Generate logs

In order to generate logs and see them in the graylog web interface do following:  

### 1. Bring up mongodb, elasticsearch anf graylog executing:

```
docker-compose up -d --build
```

Wait to see message `Graylog server up and running`.

### 2. Start Graylog Input
   
Open in browser http://localhost:9000/ and login with credentials admin/admin.
Then open in upper menu `System/Inputs` and select `GELF TCP` and click `Launch new input` button.
Then select node and set some title and click `Save` button.

### 3. Start App

Start via `IDE` or execute in command line:
```
mvn spring-boot:run
```

### 4. Open hello endpoint

Open in the browser localhost:8080/hello

### 5. See message in in Graylog page  
On the Graylog page click on `Show received messages` button. Messages should appear.


## Swagger

You can find endpoints info on swagger:  
http://localhost:8080/swagger-ui.html#


## Local environment, docker-compose

To bring up environment execute from root:
```
docker-compose up -d --build
```

- `-d` Detached mode: Run containers in the background, print new container names.
- `--build` iBuild images before starting containers
- `--remove-orphans` Remove containers for services not defined
  in the Compose file

This will bring up:
- mongodb
- elastichsearch
- graylog


To check status of running containers execute from same foldeer:
```
docker-compose ps
```


To stop all running containers execute:
```
docker-compose stop
```

To stop all running containers and remove resources execute:
```
docker-compose down
```


To remove all resouces, cleanup execute: 
```
docker system prune --all --force --volumes
```

## Local environment, MicroK8s

For local setup of `MicroK8s` check https://microk8s.io/