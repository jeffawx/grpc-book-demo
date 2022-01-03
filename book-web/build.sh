#!/bin/bash

./gradlew bootBuildImage
docker tag book-web:0.0.1-SNAPSHOT awx-dev-03.awx.im:5000/book-web
docker push awx-dev-03.awx.im:5000/book-web
