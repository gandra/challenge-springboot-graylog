## Install microk8s
   ``` bash
   sudo snap install microk8s --classic
   ``` 

## Install microk8s instraction
1. microk8s enable ingress
2. microk8s.enable dns
3. microk8s enable storage
4. microk8s enable registry
sudo nano /var/snap/microk8s/current/args/containerd-template.toml
registry settings
microk8s stop
microk8s start



## Install mongodb operator

1. git clone https://github.com/mongodb/mongodb-kubernetes-operator.git
2. kubectl create ns mongodb
3. kubectl apply -f deploy/clusterwide
4. kubectl apply -k config/rbac --namespace mongodb
5. kubectl apply -f config/crd/bases/mongodbcommunity.mongodb.com_mongodbcommunity.yaml
6. kubectl apply -k config/rbac/ --namespace mongodb
kubectl get role mongodb-kubernetes-operator --namespace mongodb
kubectl get rolebinding mongodb-kubernetes-operator --namespace mongodb
kubectl get serviceaccount mongodb-kubernetes-operator --namespace mongodb
kubectl create -f config/manager/manager.yaml --namespace mongodb
kubectl get pods --namespace mongodb
## Edit config/samples/mongodb.com_v1_mongodbcommunity_cr.yaml to replica and password
kubectl apply -f config/samples/mongodb.com_v1_mongodbcommunity_cr.yaml --namespace mongodb
kubectl create secret generic example-mongodb-config --from-file=cluster-config.json --namespace mongodb
kubectl delete pod example-mongodb-0 example-mongodb-1 example-mongodb-2


### Elasticsearch And Graylog

 cd configuration_files/graylog
 kubectl apply -f .
 Ayağa kalktıktan sonra arayüze girip inputlardan gelf tcp'yi 12222 portundan, syslog'u da 1514 numaralı porttan açıyoruz.
 
 graylog.graylog.svc.cluster.local 
 