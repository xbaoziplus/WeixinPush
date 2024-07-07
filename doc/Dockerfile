#依赖java8
#openjdk:8 526M
#FROM openjdk:8
#tomcat:8-jre8 240M
FROM tomcat:8-jre8
#创建者
MAINTAINER testUser
#暴露端口
EXPOSE 8520
# 设置工作目录
WORKDIR /levam
#不确定jar名字可以写*.jar 即为所以
COPY *.jar /levam
COPY lib /levam/lib
#挂载目录
#VOLUME /tmp
#容器已启动 默认运行命令 java -jar app.jar
#生产环境启动 可以写#容器已启动 默认运行命令 java -jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]
#解决OOM问题 指定内存初始128M 最大300M ENTRYPOINT ["java","-jar","-Xms128m","-Xmx300m","/app.jar","--spring.profiles.active=prod"]
ENTRYPOINT ["java","-jar","/levam/app.jar"]
