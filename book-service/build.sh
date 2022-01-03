#!/bin/bash

./gradlew bootBuildImage
docker tag book-app:0.0.1-SNAPSHOT awx-dev-03.awx.im:5000/book-service
docker push awx-dev-03.awx.im:5000/book-service
