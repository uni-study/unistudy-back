#!/bin/bash

# mysql 컨테이너 실행
docker-compose up -d

cd unistudy
chmod +x ./gradlew
./gradlew clean build
nohup java -jar build/libs/unistudy-0.0.1-SNAPSHOT.jar > ./log.txt &
