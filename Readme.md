# Springboot demo app with graylog in docker

This is demo springboot app with graylog.
At the moment app sah only 1 dummy endpoint /hello with returns hardcoded string and logs some message using logback.
Graylog is configured via `GELF TCP Async`.

## Challenge 1:
Deploy all in kubernetes on ubuntu. 
   
#### Success Criteria: 
Generate logs in app. See logs appears in graylog.  

## Challenge 2:
Upgrade containers to latest mongo, graylog and elasticsearch.
#### Success Criteria:
You can bring env `up with docker-compose` with upgraded containers + `Challenge 1 Sucess Criteria`

## Challenge 3:
Deploy mongodb in kubernetes cluster using MongoDB Community Kubernetes Operator and separate read and write over mongo nodes
#### Success Criteria:
- Instruction how to setup k8s on ubuntu and java 11 or 15
- All deployed on k8s on ubuntu: demoapp, mongodb, graylog, elasticsearch
- mongodb deployed in cluster with 3 nodes using MongoDB Community Kubernetes Operator
- Write to mongo over master node
- Read from replica nodes


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