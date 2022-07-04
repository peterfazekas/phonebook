# Kubernetes demo steps

### Check whether Kubectl is installed

```
kubectl version --output=yaml
```
### Check Kubernetes instances

```
kubectl get pods
kubectl get deployments
kubectl get replicasets
kubectl get services
```
---
## Deploy phonebook application to kubernetes Cluster

### Deploy 1-pod.yaml

#### Check 1-pod.yaml

```
cat 1-pod.yaml
```

#### Deploy phonebook Pod

```
kubectl apply -f 1-pod.yaml
```

#### Check logs

```
kubectl logs phonebook
```

#### Check pod

```
kubectl get pods
kubectl describe pods phonebook

curl -X GET <IP>:8080
```

#### Remove pod

```
kubectl delete -f 1-pod.yaml
kubectl get pods
```
---
### Deploy 2-deployment.yaml

#### Check 2-deployment.yaml

```
cat 2-deployment.yaml
```

#### Deploy phonebook deployment

```
kubectl apply -f 2-deployment.yaml
```

#### Check pod

```
kubectl get deployments
kubectl get replicaset
kubectl get pods
kubectl get services
kubectl describe deployment phonebook
kubectl describe pods <PODNAME>

curl -X GET  <IP>:8080
```

#### Check logs

```
kubectl logs <PODNAME>
```

#### Expose Service 

```
kubectl expose deployment phonebook --type=LoadBalancer --name=phonebook-service

curl -X GET localhost:8080
curl -X PATCH localhost:8080/init
curl -X GET localhost:8080

kubectl logs <PODNAME>
```

#### Remove deployment

```
kubectl delete services phonebook-service
kubectl delete -f 2-deployment.yaml

kubectl get services
kubectl get deployments
kubectl get replicaset
kubectl get pods
```
---
### Deploy 3-service.yaml

#### Check 3-service.yaml

```
cat 3-service.yaml
```

#### Deploy phonebook deployment

```
kubectl apply -f 3-service.yaml
```

#### Check pod

```
kubectl get deployments
kubectl get replicaset
kubectl get pods
kubectl get services
kubectl describe deployment phonebook
kubectl describe pods <PODNAME>

curl -X GET localhost
curl -X PATCH localhost/init
curl -X GET localhost
kubectl logs <PODNAME>
```

#### Remove deployment

```
kubectl delete -f 3-service.yaml

kubectl get services
kubectl get deployments
kubectl get replicaset
kubectl get pods
```
---
### Deploy 4-replicas.yaml

#### Check 4-replicas.yaml

```
cat 4-replicas.yaml
diff 3-service.yaml 4-replicas.yaml
diff 3-service.yaml 4-replicas.yaml -p
```

#### Deploy phonebook deployment

```
kubectl apply -f 4-replicas.yaml
```

#### Check pod

```
kubectl get deployments
kubectl get replicaset
kubectl get pods
kubectl get services
kubectl describe deployment phonebook
kubectl describe pods <PODNAME-1>
kubectl describe pods <PODNAME-2>

curl -X GET localhost
curl -X PATCH localhost/init
curl -X GET localhost
curl -X GET localhost
curl -X GET localhost
curl -X GET localhost
kubectl logs <PODNAME-1>
kubectl logs <PODNAME-2>
```

#### Remove deployment

```
kubectl delete -f 4-replicas.yaml

kubectl get services
kubectl get deployments
kubectl get replicaset
kubectl get pods
```
