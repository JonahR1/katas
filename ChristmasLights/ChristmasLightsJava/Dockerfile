FROM openjdk:19-bullseye

RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar -xvf apache-maven-3.8.6-bin.tar.gz && \
    mv apache-maven-3.8.6 /opt/ && \
    rm apache-maven-3.8.6-bin.tar.gz && \
    echo "M2_HOME='/opt/apache-maven-3.8.6'" >> ~/.bashrc && \
    echo 'PATH="$M2_HOME/bin:$PATH"' >> ~/.bashrc && \
    echo "export PATH" >> ~/.bashrc

WORKDIR /usr/src/app