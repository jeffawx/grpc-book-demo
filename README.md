# grpc-book-demo

```bash
kubectl create namespace grpc-book-demo

# optional for istio sidecar injection
kubectl label namespace grpc-book-demo istio-injection=enabled

# change default namespace, https://github.com/ahmetb/kubectx
kubens grpc-book-demo

# build & push image
./build.sh

# install apps
kubectl apply -f kubernetes.yaml

# alternatively if tilt is used, https://tilt.dev/
tilt up

# (optional) if istio is enabled
istioctl dashboard kiali

# check zipkin, http://localhost:9411
kubectl port-forward "$(kubectl get pod -l app=zipkin -o jsonpath='{.items[0].metadata.name}')" 9411
```
