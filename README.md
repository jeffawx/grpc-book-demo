# grpc-book-demo

```bash
kubectl create namespace grpc-book-demo

# optional for istio sidecar injection
kubectl label namespace grpc-book-demo istio-injection=enabled

# change default namespace, https://github.com/ahmetb/kubectx
kubens grpc-book-demo

# build & push image
cd book-service && ./build.sh && cd ..
cd book-web && ./build.sh && cd ..
```
