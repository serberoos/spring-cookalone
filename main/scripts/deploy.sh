# 해당 deploy.sh 파일은 Ops EC2에서 수행할 작업을 명시해둔 파일입니다.

# 가동중인 cookalone 도커 중단 및 삭제
sudo docker ps -a -q --filter "name=spring-cookalone" | grep -q . && docker stop spring-cookalone && docker rm spring-cookalone | true

# 기존 이미지 삭제
sudo docker rmi jae99c/spring-cookalone:1.0

# 도커 허브 이미지 pull
sudo docker pull jae99c/spring-cookalone:1.0

# 도커 run
docker-compose up
#docker run -d -p 8080:8080 --name spring-cookalone jae99c/spring-cookalone:1.0

# 사용하지 않는 불 필요한 이미지 삭제 -> 현재 컨테이너가 물고 있는 이미지는 삭제되지 않음.
docker rmi -f $(docker images -f "dangling=true" -q) || true
