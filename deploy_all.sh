#!/bin/bash
echo 'Configuring IP'
export HOST_IP=`hostname -I | awk '{print $1}'`
echo $HOST_IP

echo 'Creating war file'
mvn install

echo 'Moving war file on webapps directory'
cp ./target/innovaPacsApi.war /opt/tomcat/webapps

sudo systemctl restart tomcat

