FROM openjdk:8-alpine

WORKDIR /tests

COPY . .

RUN apk add curl tar gzip \
    && curl -L https://bintray.com/qameta/maven/download_file?file_path=io%2Fqameta%2Fallure%2Fallure-commandline%2F2.13.4%2Fallure-commandline-2.13.4.tgz | tar -xz

CMD ./gradlew clean test -Dis.remote=true -Dhub.url=http://selenium-hub:4444/wd/hub; ./allure-2.13.4/bin/allure serve /tests/build/allure-results -p 1111