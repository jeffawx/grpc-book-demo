k8s_yaml('kubernetes.yaml')
k8s_resource('book-web', port_forwards='8888:8080')
k8s_resource('book-service', port_forwards='8889:8080')
k8s_resource('zipkin', port_forwards='9411:9411')
