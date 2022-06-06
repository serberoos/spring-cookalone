# JDK11 이미지 사용.
FROM openjdk:11-jdk

# 마운트에 /tmp를 사용하는 이유는 Spring boot의 Tomcat default 저장소가 /tmp로 볼륨 마운트를해
# 호스트의 /var/lib/docker에 임시파일을 만들고 /tmp와 연결하게 된다.
VOLUME /tmp

# JAR_FILE 변수에 값을 저장
ARG JAR_FILE=./build/libs/cookalone-1.0.jar

# 변수에 저장된 것을 컨테이너 실행시 이름을 app.jar파일로 변경해 컨테이너에 저장
COPY ${JAR_FILE} app.jar

# 빌드된 이미지가 run 될 때 실행할 명령어
ENTRYPOINT ["java","-jar","app.jar"]