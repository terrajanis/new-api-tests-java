FROM maven:3.8.2-ibmjava-8

WORKDIR /tests

COPY . .

CMD mvn -q -DsuiteXmlFile=user-suite.xml test
