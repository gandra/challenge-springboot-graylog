# Project K8S installation Doc

## Install microk8s
``` bash
sudo snap install microk8s --classic
``` 

## Install microk8s instraction

1. microk8s enable ingress

2. microk8s enable dns

3. microk8s enable storage

4. microk8s enable registry

## registry settings (to activate it)

microk8s stop

microk8s start


# enable kubeconfig
install kubectl https://kubernetes.io/docs/tasks/tools/

```
microk8s config > ~/.kube/config
export KUBECONFIG=~/.kube/config
```

make sure kubectl works properly by using

```
kubectl get pods -A
```

if you see an output that is not an error you are good to go.

## Install mongodb operator

```
git clone https://github.com/mongodb/mongodb-kubernetes-operator.git
cd mongodb-kubernetes-operator
kubectl create ns mongodb
kubectl apply -f deploy/clusterwide
kubectl apply -k config/rbac --namespace mongodb
kubectl apply -f config/crd/bases/mongodbcommunity.mongodb.com_mongodbcommunity.yaml
kubectl apply -k config/rbac/ --namespace mongodb
kubectl get role mongodb-kubernetes-operator --namespace mongodb
kubectl get rolebinding mongodb-kubernetes-operator --namespace mongodb
kubectl get serviceaccount mongodb-kubernetes-operator --namespace mongodb
kubectl create -f config/manager/manager.yaml --namespace mongodb
kubectl get pods --namespace mongodb
```

### Edit config/samples/mongodb.com_v1_mongodbcommunity_cr.yaml to replica and password

```
kubectl apply -f config/samples/mongodb.com_v1_mongodbcommunity_cr.yaml --namespace mongodb
kubectl delete secret example-mongodb-config -n mongodb 
```


Check if secret is deleted ad if deleted execute:
```
kubectl create secret generic example-mongodb-config --from-file=configuration_files/mongo/cluster-config.json --namespace mongodb
```
**NOTE**: `cluster-config.json` is in the `configuration_files/mongo/` directory

There is some bug and maybe secret not deleted. Check with commands if secret deleted: 
```
kubectl get secrets -n mongodb
kubectl get secrets -n mongodb example-mongodb-config
```

If secret not deleted edit secret, replace with base64 encoded content of `configuration_files/mongo/cluster-config.json`:
```
cat configuration_files/mongo/cluster-config.json > tempfile.txt
```
Encode with `base64` content of `tempfile.txt`
and then:
```
kubectl edit secret example-mongodb-config -n mongodb
```
and replace `data` value with encoded value, save and exit from file.

kubectl delete pod example-mongodb-0 example-mongodb-1 example-mongodb-2 --namespace mongodb

cd into you project directory:(exit from mongodb-kubernetes-operator directory)
`cd ..`

### Elasticsearch And Graylog

```
cd configuration_files/graylog
kubectl create ns graylog
kubectl apply -f . -n graylog
kubectl expose deployment/elasticsearch --port=9200 --target-port=9200 --protocol=TCP -n graylog
```

# After this command you can acces graylog dashboard in browser
```
kubectl port-forward services/graylog 9001:9001 -n graylog
```

This command redirects the ClusterIP service to external world.  
After everything is up go into the graylog interface go into http://localhost:9001. username/password admin/admin. go into the system/inputs tab in graylog interface  and set gelf tcp to 12201, gelf udp to 12201, syslog udp to 1514 and syslog tcp to 1514. 

## Deploy the API 
```
mvn clean package k8s:build k8s:push k8s:resource k8s:deploy
```

After that you should see data flow from pod logs. By making requests from API you can see the logs are visible from graylog.
 
 